/*
 * Isomorphic SmartClient
 * Version 8.0
 * Copyright(c) 1998 and beyond Isomorphic Software, Inc. All rights reserved.
 * "SmartClient" is a trademark of Isomorphic Software, Inc.
 *
 * licensing@smartclient.com
 *
 * http://smartclient.com/license
 */

// Warn if we're being loaded twice as things will be very likely to not work in this case
if (Selenium.prototype.sc_userExtensions_loaded) {
    LOG.warn("SmartClient user-extensions.js is being loaded more than once - " +
        "check for this file being included multiple times in the Selenium core extensions.");
}
Selenium.prototype.sc_userExtensions_loaded = true;

PageBot.prototype.getAutWindow = function() {
    var autWindow = this.browserbot.getUserWindow();
    
    // if the user window is the dev console, redirect to the actual app window
    if (autWindow.targetWindow != null) autWindow = autWindow.targetWindow;
    // If SmartClient isn't loaded on the target window, just bail.
    if (autWindow.isc == null) return;

    if (autWindow.isc.AutoTest === undefined) {
        //this should never be the case with newer SC versions as AutoTest is part of core
        autWindow.isc.loadAutoTest();
    } else if (autWindow.isc.Canvas.getCanvasLocatorFallbackPath === undefined) {
        autWindow.isc.ApplyAutoTestMethods();
    }
    autWindow.isc.EventHandler.useNativeEventTime = false;
    return autWindow;
};

Selenium.prototype.getAutWindow = PageBot.prototype.getAutWindow;

// All locateElementBy* methods are added as locator-strategies.
PageBot.prototype.locateElementByScID = function(idLocator, inDocument, inWindow) {
    LOG.debug("Locate Element with SC ID=" + idLocator + ", inDocument=" + inDocument + ", inWindow=" + inWindow.location.href);
    var autWindow = this.getAutWindow();

    idLocator = idLocator.replace(/'/g, "");
    idLocator = idLocator.replace(/"/g, "");

    var scObj = autWindow[idLocator];
    if(scObj == null || scObj === undefined) {
        LOG.info("Unable to locate SC element with ID " + idLocator);
        return null;
    } else {
        LOG.debug('Found SC object ' + scObj);
    }

    var scLocator = "//" + scObj.getClassName() + "[ID=\"" + idLocator + "\"]";
    LOG.debug("Using SC Locator " + scLocator);
    var elem = autWindow.isc.AutoTest.getElement(scLocator);
    LOG.info("Returning element :: " + elem + " for SC locator " + scLocator);
    return elem;
};

PageBot.prototype.locateElementByScLocator = function(scLocator, inDocument, inWindow) {
    LOG.debug("Locate Element with SC Locator=" + scLocator + ", inDocument=" + inDocument + 
        ", inWindow=" + inWindow.location.href);

    //support scLocators with the direct ID of the widget specified
    if(scLocator.indexOf("/") == -1) {
        LOG.debug("Using ID locator");
        return this.locateElementByScID(scLocator, inDocument, inWindow);
    }
    var autWindow = this.getAutWindow();
    var elem = autWindow.isc.AutoTest.getElement(scLocator);
    LOG.debug("Returning element :: " + elem + " for SC locator " + scLocator);

    return elem;
};

Selenium.prototype.orig_doType = Selenium.prototype.doType;

Selenium.prototype.doType = function(locator, value) {
    /**
   * Sets the value of an input field, as though you typed it in.
   *
   * <p>Can also be used to set the value of combo boxes, check boxes, etc. In these cases,
   * value should be the value of the option selected, not the visible text.</p>
   *
   * @param locator an <a href="#locators">element locator</a>
   * @param value the value to type
   */
   Selenium.prototype.orig_doType.call(this, locator, value);

    //Selenium doesn't actually simulate a user typing into an input box so for SmartClient FormItem's manually register the change.
    if(this.hasSC()) {
        var autWindow = this.getAutWindow(),
            formItem,
            scLocator = locator;
            
        if (scLocator.indexOf("scLocator=") != -1) {
            scLocator = scLocator.substring("scLocator=".length);
            formItem = autWindow.isc.AutoTest.getLocatorFormItem(scLocator);
        } else if (scLocator.indexOf("scID=") != -1) {
            var ID = scLocator.substring("scID=".length);
            formItem = autWindow[ID];
        }
        if(formItem != null) {
            formItem.updateValue();
        }
    }
};

Selenium.prototype.orig_doClick = Selenium.prototype.doClick;

Selenium.prototype.doClick = function(locator, eventParams)
{
    LOG.info("Located in doScClick : " + locator);
    var isSCLocator = locator && 
                        ((locator.substring(0, "scLocator".length) == "scLocator") ||
                         (locator.substring(0, "scID".length) == "scID")),
        element = this.page().findElement(locator);
    if(this.hasSC() && isSCLocator) {
        var autWindow = this.getAutWindow();
      
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        //if the clicked element does not correspond to a SmartClient widget, then perform the default SmartClient click operation
        if(canvas == null) {
            Selenium.prototype.orig_doClick.call(this, locator, eventParams);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);

        var coords = this.getSCLocatorCoords(autWindow, locator);
        if (coords == null) return;
        var clientX = coords[0];
        var clientY = coords[1];
  
        // Ensure we explicitly indicate whether this is a second click within double-click delay
        // This makes SC logic fire double click on the second click, regardless of the
        // playback timing
        if (autWindow.isc.EH._isSecondClick == null) {
            autWindow.isc.EH._isSecondClick = false;
        }
      
        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);

        //fire a sequence of mousedown, mouseup and click operation to trigger a SmartClient click event
        this.browserbot.triggerMouseEvent(element, "mouseover", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mousedown", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mouseup", true, clientX, clientY);
        this.browserbot.clickElement(element);
        
        autWindow.isc.EH._isSecondClick = null;
    } else {
        Selenium.prototype.orig_doClick.call(this, locator, eventParams);
    }
};

// Special secondClick event - second half of a double-click
Selenium.prototype.doSecondClick = function (locator, eventParams) 
{
    if (!this.hasSC()) return this.doClick(locator, eventParams);
    
    var autWindow = this.getAutWindow();
    autWindow.isc.EH._isSecondClick = true;
    this.doClick(locator, eventParams);
    autWindow.isc.EH._isSecondClick = null;
}

// ensure playback of mouseDown / mouseUp on SmartClient locators behaves as expected.
Selenium.prototype.orig_doMouseDown = Selenium.prototype.doMouseDown;

Selenium.prototype.doMouseDown = function(locator, eventParams)
{
    LOG.info("Located in doMouseDown : " + locator);
    var isSCLocator = locator && 
                        ((locator.substring(0, "scLocator".length) == "scLocator") ||
                         (locator.substring(0, "scID".length) == "scID")),
        element = this.page().findElement(locator);
    if(this.hasSC() && isSCLocator) {
        var autWindow = this.getAutWindow();
      
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        //if the clicked element does not correspond to a SmartClient widget, then perform the default SmartClient click operation
        if(canvas == null) {
            Selenium.prototype.orig_doMouseDown.call(this, locator, eventParams);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);

        var coords = this.getSCLocatorCoords(autWindow, locator);
        if (coords == null) return;
        var clientX = coords[0];
        var clientY = coords[1];
  
      
        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);

        // fire mouseover / mouseDown
        // This will set up for SmartClient click, doubleclick or drag event as appropriate
        this.browserbot.triggerMouseEvent(element, "mouseover", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mousedown", true, clientX, clientY);
    } else {
        Selenium.prototype.orig_doMouseDown.call(this, locator, eventParams);
    }
};


Selenium.prototype.orig_doMouseUp = Selenium.prototype.doMouseUp;

Selenium.prototype.doMouseUp = function(locator, eventParams)
{
    LOG.info("Located in doMouseUp : " + locator);
    var isSCLocator = locator && 
                        ((locator.substring(0, "scLocator".length) == "scLocator") ||
                         (locator.substring(0, "scID".length) == "scID")),
        element = this.page().findElement(locator);
    if(this.hasSC() && isSCLocator) {
        var autWindow = this.getAutWindow();
      
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        //if the clicked element does not correspond to a SmartClient widget, then perform the default SmartClient click operation
        if(canvas == null) {
            Selenium.prototype.orig_doMouseUp.call(this, locator, eventParams);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);

        var coords = this.getSCLocatorCoords(autWindow, locator);
        if (coords == null) return;
        
        var clientX = coords[0];
        var clientY = coords[1];
  
        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);

        // fire mouseUp and click to trigger a SmartClient click event
        // We should have already fired mouseDown
        this.browserbot.triggerMouseEvent(element, "mouseup", true, clientX, clientY);
        this.browserbot.clickElement(element);
        
    } else {
        Selenium.prototype.orig_doMouseUp.call(this, locator, eventParams);
    }
};


Selenium.prototype.orig_doDoubleClick = Selenium.prototype.doDoubleClick;

Selenium.prototype.doDoubleClick = function(locator, eventParams)
{
    LOG.info("Locator in doDoubleClick : " + locator);
    var element = this.page().findElement(locator);
    
    if(this.hasSC()) {
        var autWindow = this.getAutWindow();
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        //if the clicked element does not correspond to a SmartClient widget, then perform the default SmartClient doubleclick operation
        if(canvas == null) {
            Selenium.prototype.orig_doDoubleClick.call(this, locator, eventParams);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);
        var coords = this.getSCLocatorCoords(autWindow, locator);
        if (coords == null) return;
        var clientX = coords[0];
        var clientY = coords[1];

        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);

        //fire a sequence of events to trigger a SmartClient doubleclick event
        this.browserbot.triggerMouseEvent(element, "mouseover", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mousedown", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mouseup", true, clientX, clientY);
        this.browserbot.clickElement(element);
        this.browserbot.triggerMouseEvent(element, "mousedown", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mouseup", true, clientX, clientY);
        this.browserbot.clickElement(element);

    } else {
        Selenium.prototype.orig_doDoubleClick.call(this, locator, eventParams);
    }
};

Selenium.prototype.orig_doContextMenu = Selenium.prototype.doContextMenu;

Selenium.prototype.doContextMenu = function(locator, eventParams)
{
    LOG.info("Locator in doContextMenu : " + locator);
    var element = this.page().findElement(locator);
    if(this.hasSC()) {
        var autWindow = this.getAutWindow();
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        if(canvas == null) {
            Selenium.prototype.orig_doContextMenu.call(this, locator, eventParams);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);

        var coords = this.getSCLocatorCoords(autWindow, scLocator);
        var clientX = coords[0];
        var clientY = coords[1];

        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);
        this.browserbot.triggerMouseEvent(element, "contextmenu", true, clientX, clientY);
    } else {
        Selenium.prototype.orig_doContextMenu.call(this, locator, eventParams);
    }
};


Selenium.prototype.hasSC = function() {
    var autWindow = this.browserbot.getUserWindow();
    if (autWindow.targetWindow != null) autWindow = autWindow.targetWindow;
    return !(autWindow.isc === undefined);
};


Selenium.prototype.orig_getTable = Selenium.prototype.getTable;

Selenium.prototype.getTable = function(tableCellAddress) {
/**
 * Gets the text from a cell of a table. The cellAddress syntax
 * tableLocator.row.column, where row and column start at 0.
 *
 * @param tableCellAddress a cell address, e.g. "foo.1.4"
 * @return string the text from the specified cell
 */

    if(this.hasSC()) {
        // This regular expression matches "tableName.row.column"
        // For example, "mytable.3.4"
        var pattern = /(.*)\.(\d+)\.(\d+)/;

        if(!pattern.test(tableCellAddress)) {
            throw new SeleniumError("Invalid target format. Correct format is tableLocator.rowNum.columnNum");
        }

        var pieces = tableCellAddress.match(pattern);

        var tableName = pieces[1];
        var row = pieces[2];
        var col = pieces[3];

        var element = this.browserbot.findElement(tableName);

        var autWindow = this.getAutWindow();

        var listGrid = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        if(listGrid == null) {
            return Selenium.prototype.orig_getTable.call(this, tableCellAddress);
        }
        //the locator can return a GridBody
        if(listGrid.grid) {
            listGrid = listGrid.grid;
        }
        
        LOG.debug("Found ListGrid " + listGrid.getClassName());
        
        var record = listGrid.getRecord(Number(row));
        LOG.debug("Record for row " + row + " is " + record);
        return listGrid.getCellValue(record, row, col);
    } else {
        Selenium.prototype.orig_getTable.call(this, tableCellAddress);
    }    
};

Selenium.prototype.orig_doMouseOver = Selenium.prototype.doMouseOver;

Selenium.prototype.doMouseOver = function(locator, eventParams) {
    /**
   * Simulates a user hovering a mouse over the specified element.
   *
   * @param locator an <a href="#locators">element locator</a>
   */

    LOG.info("Locator in doMouseOver : " + locator);
    var element = this.page().findElement(locator);
    if(this.hasSC()) {
        var autWindow = this.getAutWindow();
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        if(canvas == null) {
            Selenium.prototype.orig_doMouseOver.call(this, locator);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);

        var coords = this.getSCLocatorCoords(autWindow, locator);
        var clientX = coords[0];
        var clientY = coords[1];

        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);
        this.browserbot.triggerMouseEvent(element, "mouseover", true, clientX, clientY);
    } else {
        Selenium.prototype.orig_doMouseOver.call(this, locator);
    }

};


Selenium.prototype.orig_doMouseMove = Selenium.prototype.doMouseMove;

Selenium.prototype.doMouseMove = function(locator, eventParams) {
    /**
   * Simulates a user hovering a mouse over the specified element.
   *
   * @param locator an <a href="#locators">element locator</a>
   */

    LOG.info("Locator in doMouseMove : " + locator);
    var element = this.page().findElement(locator);
    if(this.hasSC()) {
        var autWindow = this.getAutWindow();
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        if(canvas == null) {
            Selenium.prototype.orig_doMouseMove.call(this, locator);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);

        var coords = this.getSCLocatorCoords(autWindow, locator);
        var clientX = coords[0];
        var clientY = coords[1];

        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);
        autWindow.isc.EH.immediateMouseMove = true;
        this.browserbot.triggerMouseEvent(element, "mousemove", true, clientX, clientY);
        autWindow.isc.EH.immediateMouseMove = null;
    } else {
        Selenium.prototype.orig_doMouseMove.call(this, locator);
    }

};

// Override drag and drop for SC components
Selenium.prototype.orig_doDragAndDrop = Selenium.prototype.doDragAndDrop;
Selenium.prototype.doDragAndDrop = function (locator, eventParams) {
    var isSCLocator = locator && 
                        ((locator.substring(0, "scLocator".length) == "scLocator") ||
                         (locator.substring(0, "scID".length) == "scID")),
        element = this.page().findElement(locator);
    if (this.hasSC() && isSCLocator) {
        var autWindow = this.getAutWindow();
      
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        //if the clicked element does not correspond to a SmartClient widget, then perform the default SmartClient click operation
        if(canvas == null) {
            Selenium.prototype.orig_doDragAndDrop.call(this, locator, eventParams);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);

        var coords = this.getSCLocatorCoords(autWindow, locator);
        if (coords == null) return;
        var clientX = coords[0];
        var clientY = coords[1];
  
      
        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);

        autWindow.isc.EH.immediateMouseMove = true;

        // fire mouseover / mouseDown / mousemove at original coordinates
        this.browserbot.triggerMouseEvent(element, "mouseover", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mousedown", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mousemove", true, clientX, clientY);
        // now trigger mousemove and mouseup at drop coordinates
        // eventParams should contain offset as string like "+100,-25"
        var delta = eventParams.split(",");
        clientX += parseInt(delta[0]);
        clientY += parseInt(delta[1]);
        
        this.browserbot.triggerMouseEvent(element, "mousemove", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mouseup", true, clientX, clientY);
        
        autWindow.isc.EH.immediateMouseMove = null;

    } else {
        Selenium.prototype.orig_doDragAndDrop.call(this, locator, eventParams);
    }
}

Selenium.prototype.orig_doDragAndDropToObject = Selenium.prototype.dragAndDropToObject;
Selenium.prototype.doDragAndDropToObject = function (locator, targetLocator) {
    var isSCLocator = locator && 
                        ((locator.substring(0, "scLocator".length) == "scLocator") ||
                         (locator.substring(0, "scID".length) == "scID")),
        element = this.page().findElement(locator);
        
    if (this.hasSC() && isSCLocator) {
        var autWindow = this.getAutWindow();
        var canvas = autWindow.isc.AutoTest.locateCanvasFromDOMElement(element);
        //if the clicked element does not correspond to a SmartClient widget, then perform the default SmartClient click operation
        if(canvas == null) {
            Selenium.prototype.orig_doDragAndDropToObject.call(this, locator, targetLocator);
            return;
        }
        LOG.debug("Located canvas " + canvas + " for locator " + locator);
        var coords = this.getSCLocatorCoords(autWindow, locator);
        if (coords == null) return;

        var clientX = coords[0];
        var clientY = coords[1];
  
      
        LOG.debug("clientX = " + clientX + ", clientY=" + clientY);

        autWindow.isc.EH.immediateMouseMove = true;

        // fire mouseover / mouseDown / mousemove at original coordinates
        this.browserbot.triggerMouseEvent(element, "mouseover", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mousedown", true, clientX, clientY);
        this.browserbot.triggerMouseEvent(element, "mousemove", true, clientX, clientY);
        // now trigger mousemove and mouseup at drop coordinates
        
        var dropElement = this.page().findElement(targetLocator)
        var isSCTarget = targetLocator.indexOf("scLocator") != -1;
        if (isSCTarget) {
            var targetCoords = this.getSCLocatorCoords(autWindow, targetLocator);
            if (targetCoords != null) coords = targetCoords;
        // In this case we've got a drag from a SmartClient component to some arbitrary
        // element on the page.
        } else {
            var targetLeft = autWindow.isc.Element.getLeftOffset(dropElement);
            var targetTop = autWindow.isc.Element.getTopOffset(dropElement);
            coords = [targetLeft,targetTop];
        }

        this.browserbot.triggerMouseEvent(dropElement, "mouseover", true, coords[0], coords[1]);
        this.browserbot.triggerMouseEvent(dropElement, "mousemove", true, coords[0], coords[1]);
        this.browserbot.triggerMouseEvent(dropElement, "mouseup", true, coords[0], coords[1]);
        
        autWindow.isc.EH.immediateMouseMove = null;

    } else {
        Selenium.prototype.orig_doDragAndDropToObject.call(this, locator, targetLocator);
    }
    
}

Selenium.prototype.getSCLocatorCoords = function (autWindow, scLocator) {
    if (scLocator.indexOf("scLocator=") != -1) {
        scLocator = scLocator.substring("scLocator=".length);
        var coords = autWindow.isc.AutoTest.getPageCoords(scLocator);
        LOG.debug("Determining page coordinates for SC Locator:" + scLocator + ": " + coords);
        return coords;
    } else if (scLocator.indexOf("scID=") != -1) {
        var ID = scLocator.substring("scID=".length);
        var canvas = autWindow[ID];
        if (canvas != null && autWindow.isc.isA.Canvas(canvas) &&
            canvas.isDrawn() && canvas.isVisible()) 
        {
            var left = canvas.getPageLeft() + parseInt(canvas.getVisibleWidth()/2),
                top = canvas.getPageTop() + parseInt(canvas.getVisibleHeight()/2);
            LOG.debug("Determining page coordinates for SC canvas:" + ID + ": " + [left,top]);
            return [left,top];
        }
    }
    LOG.debug("Unable to determine page coordinates for SC Locator:" + scLocator);
    return null;
}

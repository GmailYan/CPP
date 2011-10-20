package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class NavigationPaneSection extends SectionStackSection {
	private ListGrid listGrid;
	  private NavigationPaneRecord[] sectionData;
	  
	  public NavigationPaneSection(String sectionName, NavigationPaneRecord[] sectionData, 
			  					   RecordClickHandler clickHandler) {
	  	super(sectionName);
	  				
	  	GWT.log("init NavigationPaneSection()...", null);
	  	
	  	this.sectionData = sectionData;
	  	
		// initialise the List Grid  
	  	listGrid = new ListGrid();  
	  	listGrid.setBaseStyle("crm-NavigationPaneGridCell");  
	  	listGrid.setWidth("100%");  
	  	listGrid.setHeight("100%");  
	  	listGrid.setShowAllRecords(true);  
	  	listGrid.setShowHeader(false);
	  		
	  	// initialise the Icon field
//	  	ListGridField appIconField = new ListGridField("icon", "Icon", 27);  
//	  	appIconField.setImageSize(16); 
//	  	appIconField.setAlign(Alignment.RIGHT);
//	  	appIconField.setType(ListGridFieldType.IMAGE);  
//	  	appIconField.setImageURLPrefix("icons/16/");  
//	  	appIconField.setImageURLSuffix(".png");  
//	  	appIconField.setCanEdit(false);  
	  	
	  	// initialise the Name field
	  	ListGridField appNameField = new ListGridField("name", "Name");  
	  	   
	  	// add the fields to the list Grid
//	  	listGrid.setFields(new ListGridField[] {appIconField, appNameField});  
	  	listGrid.setFields(new ListGridField[] {appNameField});  
	  	
	  	// set up the column data
	  	listGrid.setData(sectionData);  
	  	listGrid.selectRecord(0);
	  	
	  	// register the click handler
	  	listGrid.addRecordClickHandler(clickHandler);
	  	
	    // section.setItems(appList);
	    this.addItem(listGrid);
	    this.setExpanded(true);  
	  }
	  
	  public ListGrid getListGrid() {
		return listGrid;
	  }

	  public void selectRecord(String name) {
	    for (int i = 0; i < this.sectionData.length; i++) { 
	   	  NavigationPaneRecord record = this.sectionData[i];
	      	
	   	  if (name.contentEquals(record.getName())) {
	   		GWT.log("selectRecord->name.contentEquals(record.getName())", null);
	   		getListGrid().deselectAllRecords();
	   		getListGrid().selectRecord(i); 
	   	  }
	    }
	  }
	    
	  public int getRecord(String appName) {
		int result = -1;
	    for (int i = 0; i < this.sectionData.length; i++) { 
	      NavigationPaneRecord record = this.sectionData[i];
	  	    	
	  	  if (appName.contentEquals(record.getName())) {
	  	    GWT.log("selectRecord->name.contentEquals(record.getName())", null);
	  	    result = i;
	  	  }
	    }
	      
	    return result;
	  }

	  public void setSectionData(NavigationPaneRecord[] sectionData) {
	    this.sectionData = sectionData;
	  }

	  public NavigationPaneRecord[] getSectionData() {
		return sectionData;
	  }
}

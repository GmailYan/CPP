package ic.doc.cpp.student.client.core;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;

public class ListgridSectionStackSectionRecord extends ListGridRecord {
	 public ListgridSectionStackSectionRecord() {}
		
	  public ListgridSectionStackSectionRecord(String icon, String name,
			  					  CellDoubleClickHandler clickHandler) {
	    setIcon(icon);
		setName(name);
		setDoubleClickHandler(clickHandler);
	  }	
		    
	  public void setIcon(String appIcon) {
	    setAttribute("icon", appIcon);
	  }       
		    
	  public void setName(String appName) {
	    setAttribute("name", appName);
	  }
		  
	  public void setDoubleClickHandler(CellDoubleClickHandler clickHandler) {
		setAttribute("clickHandler", clickHandler);
	  }    

	  public String getIcon() {
	    return getAttributeAsString("icon");
	  }    

	  public String getName() {
	    return getAttributeAsString("name");
	  }
		  
	  public CellDoubleClickHandler getDoubleClickHandler() {
		return (CellDoubleClickHandler) getAttributeAsObject("clickHandler");
	  }  
}

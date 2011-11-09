package ic.doc.cpp.student.client.core;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;

public class ListgridSectionStackSectionRecord extends ListGridRecord {
	 public ListgridSectionStackSectionRecord() {}
		
	  public ListgridSectionStackSectionRecord(String place, String name,
			  					  CellDoubleClickHandler clickHandler) {
	    setPlace(place);
		setName(name);
		setDoubleClickHandler(clickHandler);
	  }	
		    
	  public void setPlace(String place) {
	    setAttribute("place", place);
	  }       
		    
	  public void setName(String appName) {
	    setAttribute("name", appName);
	  }
		  
	  public void setDoubleClickHandler(CellDoubleClickHandler clickHandler) {
		setAttribute("clickHandler", clickHandler);
	  }    

	  public String getPlace() {
	    return getAttributeAsString("place");
	  }    

	  public String getName() {
	    return getAttributeAsString("name");
	  }
		  
	  public CellDoubleClickHandler getDoubleClickHandler() {
		return (CellDoubleClickHandler) getAttributeAsObject("clickHandler");
	  }  
}

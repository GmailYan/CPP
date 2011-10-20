package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.types.ListGridFieldType;

public class ContextAreaListGrid extends ListGrid {

  public ContextAreaListGrid() {
	super();
		   
	GWT.log("init ContextAreaListGrid()...", null);

    // initialise the List Grid
	this.setShowAllRecords(true);  	
	this.setSortField(1); 
    this.setShowFilterEditor(true);  
    this.setFilterOnKeypress(true);  
    
    // initialise the List Grid fields	
//	ListGridField iconField = new ListGridField("icon", "#", 27);
//	iconField.setImageSize(16); 
//	iconField.setAlign(Alignment.CENTER);
//	iconField.setType(ListGridFieldType.IMAGE);  
//	iconField.setImageURLPrefix("icons/16/");  
//	iconField.setImageURLSuffix(".png");  
		
	ListGridField accountNameField = new ListGridField("companyName", "Company Name", 320);  
	ListGridField mainPhoneField = new ListGridField("sector", "Sector", 100);  
	ListGridField locationField = new ListGridField("location", "Location", 100);  
	ListGridField primaryContactField = new ListGridField("website", "Website", 140); 
	primaryContactField.setType(ListGridFieldType.LINK);  
	ListGridField emailPrimaryContactField = new ListGridField("emailPrimaryContact", 
															   "Email (Primary Contact)", 180);  	
	ListGridField emptyField = new ListGridField("emptyField", " ");  	
		
    // set the fields into the List Grid	
//	this.setFields(new ListGridField[] {iconField, accountNameField, mainPhoneField, locationField, 
//			primaryContactField, emailPrimaryContactField, emptyField });  
	this.setFields(new ListGridField[] {accountNameField, mainPhoneField, locationField, 
				   primaryContactField, emailPrimaryContactField, emptyField });  
	
	// populate the List Grid
	this.setData(AccountData.getRecords());  
  }
}
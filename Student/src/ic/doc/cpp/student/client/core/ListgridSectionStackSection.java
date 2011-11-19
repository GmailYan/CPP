package ic.doc.cpp.student.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class ListgridSectionStackSection extends SectionStackSection {
	 private ListGrid listGrid;
	 private ListgridSectionStackSectionRecord[] sectionData;
 
	 public ListgridSectionStackSection(String sectionName, ListgridSectionStackSectionRecord[] sectionData) {
		 super(sectionName);
	  				
		 GWT.log("init NavigationPaneSection()...", null);
		 this.sectionData = sectionData;
	  	
		 // initialise the List Grid  
		 listGrid = new ListGrid();  
	  	 listGrid.setBaseStyle("crm-NavigationPaneGridCell");  
	  	 listGrid.setWidth100();  
	  	 listGrid.setHeight100();  
	  	 listGrid.setShowAllRecords(true);  
	  	 listGrid.setShowHeader(false);
	  		
	  	// initialise the Icon field
	  	ListGridField appIconField = new ListGridField("icon", "Icon", 27);  
	  	appIconField.setImageSize(16); 
	  	appIconField.setAlign(Alignment.RIGHT);
	  	appIconField.setType(ListGridFieldType.IMAGE);  
	  	appIconField.setImageURLPrefix("icons/16/");  
	  	appIconField.setImageURLSuffix(".png");  
	  	appIconField.setCanEdit(false);  
	  	
	  	// initialise the Name field
	  	ListGridField appNameField = new ListGridField("name", "Name");  
	  	   
	  	// add the fields to the list Grid
	  	listGrid.setFields(new ListGridField[] {appIconField, appNameField});  
	  	
	  	// set up the column data
	  	listGrid.setData(sectionData);  
	  	listGrid.selectRecord(0);
	  	
	    // section.setItems(appList);
	    this.addItem(listGrid);
	    this.setExpanded(true);  
	  }
	  
	  public ListGrid getListGrid() {
		return listGrid;
	  }

	  public void selectRecord(String name) {
	    for (int i = 0; i < this.sectionData.length; i++) { 
	    	ListgridSectionStackSectionRecord record = this.sectionData[i];
	      	
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
	    	ListgridSectionStackSectionRecord record = this.sectionData[i];
	  	    	
	  	  if (appName.contentEquals(record.getName())) {
	  	    GWT.log("selectRecord->name.contentEquals(record.getName())", null);
	  	    result = i;
	  	  }
	    }
	      
	    return result;
	  }

	  public void setSectionData(ListgridSectionStackSectionRecord[] sectionData) {
	    this.sectionData = sectionData;
	  }

	  public ListgridSectionStackSectionRecord[] getSectionData() {
		  return sectionData;
	  }

	  public HandlerRegistration addRecordClickHandler(RecordClickHandler clickHandler) {
		  return listGrid.addRecordClickHandler(clickHandler);
	  }
}

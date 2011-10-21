package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceSequenceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class CalendarView extends VLayout {

  private static final String DESCRIPTION = "CalendarView";



  public CalendarView() {
    super();
		   
	GWT.log("init CalendarView()...", null);
	
	// initialise the Calendar View layout container
    this.addStyleName("crm-ContextArea");
    this.setWidth("*"); 	
	
	DataSource eventDS = new DataSource();  
	DataSourceSequenceField eventIdField = new DataSourceSequenceField("eventId");  
	eventIdField.setPrimaryKey(true);  
	   
	DataSourceTextField nameField = new DataSourceTextField("name");  
	DataSourceTextField descField = new DataSourceTextField("description");  
	DataSourceDateField startDateField = new DataSourceDateField("startDate");  
	DataSourceDateField endDateField = new DataSourceDateField("endDate");  
	   
	eventDS.setFields(eventIdField, nameField, descField, startDateField, endDateField);  
	eventDS.setClientOnly(true);  
	//eventDS.setTestData(CalendarData.getRecords());  
	   
	Calendar calendar = new Calendar();  
	calendar.setShowWeekends(false);  
	calendar.setShowWorkday(true);  
	calendar.setScrollToWorkday(true);  
	calendar.setDataSource(eventDS);  
	calendar.setAutoFetchData(true);  
	
    LayoutSpacer paddingTop = new LayoutSpacer(); 
    paddingTop.setHeight(8);
	
    this.addMember(paddingTop);
    this.addMember(calendar);
  }
  
  public static class Factory implements ContextAreaFactory {
		
	private String id;
	private static Canvas view;
		  
	public Canvas create() {
		if (view == null)
			view = new CalendarView();
	  id = view.getID();
		      
	  GWT.log("CalendarView.Factory.create()->view.getID() - " + id, null);
      return view;
    }

	public String getID() {
	  return id;
	}

    public String getDescription() {
      return DESCRIPTION;
    }
  }
}
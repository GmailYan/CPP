package ic.doc.cpp.student.client.core.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class EventDetailDataSource extends DataSource {
	private static EventDetailDataSource instance = null;  
	  
	public static EventDetailDataSource getInstance() {  
	    if (instance == null) {  
	        instance = new EventDetailDataSource("eventDetails");  
	    }  
	    return instance;  
	}
	
	public EventDetailDataSource(String id) {  
		setID(id);  

		DataSourceIntegerField pkField = new DataSourceIntegerField("eventId");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);  
  
        DataSourceTextField titleField = new DataSourceTextField("title", "Title", 200, true);  
  
        DataSourceTextField descriptionField = new DataSourceTextField("description", "Description", 255);  
      
        DataSourceIntegerField categoryIdField = new DataSourceIntegerField("categoryId");
        categoryIdField.setHidden(true);

        DataSourceTextField categoryField = new DataSourceTextField("category", "Category", 100, true);  
        
        
        DataSourceIntegerField cidField = new DataSourceIntegerField("companyId");
        cidField.setHidden(true);
        
        DataSourceTextField companyNameField = new DataSourceTextField("companyName", "Company Name", 200, true); 
        
        DataSourceTextField websiteField = new DataSourceTextField("website", "Website", 128, true);
        
        DataSourceImageField pictureField = new DataSourceImageField("picture", "Picture");
        pictureField.setImageURLPrefix("companyLogo/");
        
        DataSourceDateField startDateField = new DataSourceDateField("startDate", "Start Date");
        
        DataSourceDateField endDateField = new DataSourceDateField("endDate", "End Date");
        
        setFields(pkField, titleField, descriptionField, categoryIdField, 
        		categoryField, cidField, companyNameField, websiteField,
        		pictureField, startDateField, endDateField);  
  
        setClientOnly(true);
    }  
}

package ic.doc.cpp.student.client.core.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class EventDetailXmlDS extends DataSource {
	private static EventDetailXmlDS instance = null;  
	  
	public static EventDetailXmlDS getInstance() {  
	    if (instance == null) {  
	        instance = new EventDetailXmlDS("eventDetailDS");  
	    }  
	    return instance;  
	}  
	
    public EventDetailXmlDS(String id) {  
        setID(id);  
        setRecordXPath("/List/eventDetail");
        
        DataSourceIntegerField pkField = new DataSourceIntegerField("EID");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);  
  
        DataSourceTextField titleField = new DataSourceTextField("title", "Title", 128, true);  
  
        DataSourceTextField descriptionField = new DataSourceTextField("description", "Description", 2000);  
      
        DataSourceTextField categoryField = new DataSourceTextField("category", "Category", 128, true);  
        categoryField.setForeignKey("eventCategoryDS.categoryName");  

        DataSourceIntegerField cidField = new DataSourceIntegerField("CID");
        cidField.setHidden(true);
        cidField.setForeignKey("companyDetailDS.CID");
        
        DataSourceTextField companyNameField = new DataSourceTextField("companyName", "Company Name", 128, true); 
        
        DataSourceTextField websiteField = new DataSourceTextField("website", "Website", 128, true);
        
        DataSourceImageField pictureField = new DataSourceImageField("picture", "Picture");
        pictureField.setImageURLPrefix("companyLogo/");
        
        DataSourceDateField startDateField = new DataSourceDateField("startDate", "Start Date");
        
        DataSourceDateField endDateField = new DataSourceDateField("endDate", "End Date");
        
        setFields(pkField, titleField, descriptionField, categoryField, cidField, companyNameField, websiteField,
        		pictureField, startDateField, endDateField);  
  
        setDataURL("ds/test_data/eventDetail.data.xml");
        setClientOnly(true);
    }  
}

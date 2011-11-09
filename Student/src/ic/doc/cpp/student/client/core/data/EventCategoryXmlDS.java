package ic.doc.cpp.student.client.core.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class EventCategoryXmlDS extends DataSource {
	private static EventCategoryXmlDS instance = null;  
	  
    public static EventCategoryXmlDS getInstance() {  
        if (instance == null) {  
            instance = new EventCategoryXmlDS("eventCategoryDS");  
        }  
        return instance;  
    }
    
    public EventCategoryXmlDS(String id) {  
    	  
        setID(id);  
        setRecordXPath("/List/eventCategory");  
  
  
        DataSourceTextField itemNameField = new DataSourceTextField("categoryName", "Category Name", 128, true);  
        itemNameField.setPrimaryKey(true);  
  
        DataSourceTextField parentField = new DataSourceTextField("parentID", null);  
        parentField.setHidden(true);  
        parentField.setRequired(true);  
        parentField.setRootValue("root");  
        parentField.setForeignKey("eventCategoryDS.categoryName");  
  
  
        setFields(itemNameField, parentField);  
  
        setDataURL("ds/test_data/eventCategory.data.xml");  
          
        setClientOnly(true);  
  
    }  
}

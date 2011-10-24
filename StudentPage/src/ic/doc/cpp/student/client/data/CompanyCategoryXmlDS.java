package ic.doc.cpp.student.client.data;  
  
import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.fields.DataSourceTextField;  
  
public class CompanyCategoryXmlDS extends DataSource {  
  
    private static CompanyCategoryXmlDS instance = null;  
  
    public static CompanyCategoryXmlDS getInstance() {  
        if (instance == null) {  
            instance = new CompanyCategoryXmlDS("companyCategoryDS");  
        }  
        return instance;  
    }  
  
    public CompanyCategoryXmlDS(String id) {  
  
        setID(id);  
        setRecordXPath("/List/companyCategory");  
  
  
        DataSourceTextField itemNameField = new DataSourceTextField("categoryName", "Category Name", 128, true);  
        itemNameField.setPrimaryKey(true);  
  
        DataSourceTextField parentField = new DataSourceTextField("parentID", null);  
        parentField.setHidden(true);  
        parentField.setRequired(true);  
        parentField.setRootValue("root");  
        parentField.setForeignKey("companyCategoryDS.categoryName");  
  
  
        setFields(itemNameField, parentField);  
  
        setDataURL("ds/test_data/companyCategory.data.xml");  
          
        setClientOnly(true);  
  
    }  
}  
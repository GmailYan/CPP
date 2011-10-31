package ic.doc.cpp.student.client.core.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class CompanyDetailXmlDS extends DataSource {
	private static CompanyDetailXmlDS instance = null;  
	  
	public static CompanyDetailXmlDS getInstance() {  
	    if (instance == null) {  
	        instance = new CompanyDetailXmlDS("companyDetailDS");  
	    }  
	    return instance;  
	}  
	
    public CompanyDetailXmlDS(String id) {  
        setID(id);  
        setRecordXPath("/List/companyDetail");
        
        DataSourceIntegerField pkField = new DataSourceIntegerField("CID");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);  
  
        DataSourceTextField companyNameField = new DataSourceTextField("name", "Name", 128, true);  
  
        DataSourceTextField descriptionField = new DataSourceTextField("description", "Description", 2000);  
        DataSourceTextField categoryField = new DataSourceTextField("category", "Category", 128, true);  
        categoryField.setForeignKey("companyCategoryDS.categoryName");  
        
        DataSourceTextField websiteField = new DataSourceTextField("website", "Website", 128, true);
        
        DataSourceImageField pictureField = new DataSourceImageField("logo", "Logo");
        pictureField.setImageURLPrefix("companyLogo/");
        
        setFields(pkField, companyNameField, descriptionField, categoryField, websiteField, pictureField);  
        
        setDataURL("ds/test_data/companyDetail.data.xml");
        setClientOnly(true);          
    }  
}

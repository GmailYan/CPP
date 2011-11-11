package ic.doc.cpp.student.client.core.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class CompanyDetailDataSource extends DataSource {
	
	private static CompanyDetailDataSource instance = null;  
	  
	public static CompanyDetailDataSource getInstance() {  
	    if (instance == null) {  
	        instance = new CompanyDetailDataSource("companyDetails");  
	    }  
	    return instance;  
	}
	
	public static void newDataSource() {
		instance = new CompanyDetailDataSource("companyDetails");  
	}
	
	public CompanyDetailDataSource(String id) {
		setID(id);
		DataSourceIntegerField pkField = new DataSourceIntegerField("companyId");  
		pkField.setHidden(true);  
		pkField.setPrimaryKey(true);  
		DataSourceTextField companyNameField = new DataSourceTextField("name", "Company Name", 200, true);  
		DataSourceTextField descriptionField = new DataSourceTextField("description", "Description", 500);
		DataSourceIntegerField categoryIdField = new DataSourceIntegerField("companyCategoryId");
		categoryIdField.setHidden(true);
		DataSourceTextField categoryNameField = new DataSourceTextField("category", "Category", 100, true);  
		DataSourceTextField websiteField = new DataSourceTextField("website", "Website", 200, true);
		DataSourceImageField pictureField = new DataSourceImageField("logo", "Logo");
		pictureField.setImageURLPrefix("companyLogo/");
		setFields(pkField, companyNameField, descriptionField, categoryNameField, websiteField, pictureField, categoryIdField);
		setClientOnly(true);
	}
}

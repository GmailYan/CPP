package ic.doc.cpp.student.server;

import ic.doc.cpp.student.server.dao.CompanyCategoryDao;
import ic.doc.cpp.student.server.domain.CompanyCategory;

import java.util.List;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class CompanyCategoryTest {
	private long startTimeMillis;
	
	@Before
	public void setUp() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		startTimeMillis = System.currentTimeMillis();
	}

	@After
	public void tearDown() throws Exception {
	    long endTimeMillis = System.currentTimeMillis();
	    float durationSeconds = (endTimeMillis - startTimeMillis) / 1000F;
	    Log.debug("Duration: " + durationSeconds + " seconds");   
	}

	@Test
	public void testCompanyCategoryDao() {
		try {
			Log.debug("testCompanyCategoryDao()");
			
			createCompanyCategory();
			updateCompanyCategory();
			deleteCompanyCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteCompanyCategory() {
		Log.debug("deleteCompanyCategory()");
		    
		CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
		
		companyCategoryDao.deleteCompanyCategory(companyCategoryDao.retrieveCompanyCategory(9L));
		
		List<CompanyCategory> categorys = companyCategoryDao.retrieveCompanyCategorys();
		
		for (CompanyCategory category : categorys) {
		  Log.debug(category.toString());
		}  
		
	}

	private void updateCompanyCategory() {
		Log.debug("updateCompanyCategory()");
	    
		CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
	    
		CompanyCategory category1 = companyCategoryDao.retrieveCompanyCategory(1L);
	    Log.debug("accountDao.retrieveAccount(1L) - " + category1.toString());
	    category1.setCategoryName("Banking");
	    
	    CompanyCategory category2 = companyCategoryDao.updateCompanyCategory(category1);
	    Log.debug("accountDao.updateAccount(company1) - " + category2.toString());
	    
	    List<CompanyCategory> categorys = companyCategoryDao.retrieveCompanyCategorys();
	    
	    for (CompanyCategory category : categorys) {
	      Log.debug(category.toString());
	    }  
	}

	private void createCompanyCategory() {
		Log.debug("createCompanyCategory()");
	    
		CompanyCategoryDao accountDao = new CompanyCategoryDao();

	    CompanyCategory category2 = new CompanyCategory();
	    category2.setCategoryName("Bankin");
	    category2.setParentId(0L);
	    accountDao.createCompanyCategory(category2);
	    
	    CompanyCategory category3 = new CompanyCategory();
	    category3.setCategoryName("Retail Banking");
	    category3.setParentId(category2.getCategoryId());
	    accountDao.createCompanyCategory(category3);
	    
	    CompanyCategory category4 = new CompanyCategory();
	    category4.setCategoryName("Investment Banking");
	    category4.setParentId(category2.getCategoryId());
	    accountDao.createCompanyCategory(category4);
	    
	    CompanyCategory category5 = new CompanyCategory();
	    category5.setCategoryName("IT");
	    category5.setParentId(0L);
	    accountDao.createCompanyCategory(category5);
	    
	    CompanyCategory category6 = new CompanyCategory();
	    category6.setCategoryName("Software");
	    category6.setParentId(category5.getCategoryId());
	    accountDao.createCompanyCategory(category6);
	    
	    CompanyCategory category7 = new CompanyCategory();
	    category7.setCategoryName("Web Service");
	    category7.setParentId(category6.getCategoryId());
	    accountDao.createCompanyCategory(category7);
	    
	    CompanyCategory category8 = new CompanyCategory();
	    category8.setCategoryName("Operating System");
	    category8.setParentId(category6.getCategoryId());
	    accountDao.createCompanyCategory(category8);
	    
	    CompanyCategory category9 = new CompanyCategory();
	    category9.setCategoryName("Hardware");
	    category9.setParentId(category5.getCategoryId());
	    accountDao.createCompanyCategory(category9);

	    CompanyCategory category10 = new CompanyCategory();
	    category10.setCategoryName("Test deleting");
	    category10.setParentId(0L);
	    accountDao.createCompanyCategory(category10);
	    
	    List<CompanyCategory> categorys = accountDao.retrieveCompanyCategorys();
	    
	    for (CompanyCategory categroy : categorys) {
	      Log.debug(categroy.toString());
	    }
	}
}

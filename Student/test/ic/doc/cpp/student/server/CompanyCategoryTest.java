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
	    category1.setCategoryName("All/Banking");
	    
	    CompanyCategory category2 = companyCategoryDao.updateCompanyCategory(category1);
	    Log.debug("accountDao.updateAccount(company1) - " + category2.toString());
	    
	    List<CompanyCategory> categorys = companyCategoryDao.retrieveCompanyCategorys();
	    
	    for (CompanyCategory category : categorys) {
	      Log.debug(category.toString());
	    }  
	}

	private void createCompanyCategory() {
		Log.debug("createCompanyCategory()");
	    
		CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();

	    CompanyCategory category2 = new CompanyCategory();
	    category2.setCategoryName("All/Bankin");
	    category2.setParentId(0L);
	    companyCategoryDao.createCompanyCategory(category2);
	    
	    CompanyCategory category3 = new CompanyCategory();
	    category3.setCategoryName("All/Banking/Retail Banking");
	    category3.setParentId(category2.getCategoryId());
	    companyCategoryDao.createCompanyCategory(category3);
	    
	    CompanyCategory category4 = new CompanyCategory();
	    category4.setCategoryName("All/Banking/Investment Banking");
	    category4.setParentId(category2.getCategoryId());
	    companyCategoryDao.createCompanyCategory(category4);
	    
	    CompanyCategory category5 = new CompanyCategory();
	    category5.setCategoryName("All/IT");
	    category5.setParentId(0L);
	    companyCategoryDao.createCompanyCategory(category5);
	    
	    CompanyCategory category6 = new CompanyCategory();
	    category6.setCategoryName("All/IT/Software");
	    category6.setParentId(category5.getCategoryId());
	    companyCategoryDao.createCompanyCategory(category6);
	    
	    CompanyCategory category7 = new CompanyCategory();
	    category7.setCategoryName("All/IT/sofware/Web Service");
	    category7.setParentId(category6.getCategoryId());
	    companyCategoryDao.createCompanyCategory(category7);
	    
	    CompanyCategory category8 = new CompanyCategory();
	    category8.setCategoryName("All/IT/Software/Operating System");
	    category8.setParentId(category6.getCategoryId());
	    companyCategoryDao.createCompanyCategory(category8);
	    
	    CompanyCategory category9 = new CompanyCategory();
	    category9.setCategoryName("All/IT/Hardware");
	    category9.setParentId(category5.getCategoryId());
	    companyCategoryDao.createCompanyCategory(category9);

	    CompanyCategory category10 = new CompanyCategory();
	    category10.setCategoryName("Test deleting");
	    category10.setParentId(0L);
	    companyCategoryDao.createCompanyCategory(category10);
	    
	    List<CompanyCategory> categorys = companyCategoryDao.retrieveCompanyCategorys();
	    
	    for (CompanyCategory categroy : categorys) {
	      Log.debug(categroy.toString());
	    }
	}
}

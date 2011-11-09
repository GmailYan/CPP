package ic.doc.cpp.student.server;

import ic.doc.cpp.student.server.dao.CompanyCategoryDao;
import ic.doc.cpp.student.server.dao.CompanyDao;
import ic.doc.cpp.student.server.domain.Company;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class CompanyTest {

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
	public void testCompanyDao() {
		try {
			Log.debug("testCompanyDao()");
			createCompany();
			deleteCompany();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void deleteCompany() {
		Log.debug("deleteCompany()");
		CompanyDao companyDao = new CompanyDao();
		companyDao.deleteCompany(companyDao.retrieveCompany(6L));
	}

	private void createCompany() {
		Log.debug("createCompany()");
		CompanyDao companyDao = new CompanyDao();
		CompanyCategoryDao companyCategoryDao =  new CompanyCategoryDao();
		
		Company company1 = new Company();
		company1.setName("MorganStanley");
		company1.setCategory(companyCategoryDao.retrieveCompanyCategory(3L));
		companyDao.createCompany(company1);
		
		Company company2 = new Company();
		company2.setName("ABC");
		company2.setCategory(companyCategoryDao.retrieveCompanyCategory(2L));
		companyDao.createCompany(company2);
		
		Company company3 = new Company();
		company3.setName("Microsoft");
		company3.setCategory(companyCategoryDao.retrieveCompanyCategory(7L));
		companyDao.createCompany(company3);

		Company company4 = new Company();
		company4.setName("Google");
		company4.setCategory(companyCategoryDao.retrieveCompanyCategory(6L));
		companyDao.createCompany(company4);
		
		Company company5 = new Company();
		company5.setName("Intel");
		company5.setCategory(companyCategoryDao.retrieveCompanyCategory(8L));
		companyDao.createCompany(company5);
		
		Company company6 = new Company();
		company6.setName("TestDeleting");
		company6.setCategory(companyCategoryDao.retrieveCompanyCategory(8L));
		companyDao.createCompany(company6);
		
	}

}

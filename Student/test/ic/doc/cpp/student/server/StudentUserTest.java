package ic.doc.cpp.student.server;

import java.util.ArrayList;
import java.util.List;

import ic.doc.cpp.student.server.dao.CompanyCategoryDao;
import ic.doc.cpp.student.server.dao.CompanyDao;
import ic.doc.cpp.student.server.dao.EventDao;
import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.CompanyCategory;
import ic.doc.cpp.student.server.domain.Event;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.Security;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class StudentUserTest {

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
			createStudentUser();
			addLikeEvent();
			addLikeCompany();
			addInterestedArea();
//			deleteLikeEvent();
//			deleteLikeCompany();
//			deleteInterestedArea();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
//	private void deleteInterestedArea() {
//		StudentUserDao studentUserDao = new StudentUserDao();
//		StudentUser user = studentUserDao.retrieveUser("DuZhouzhou");
//		user.getInterestedArea().remove(0);
//		studentUserDao.updateUser(user);
//	}
//
//
//
//	private void deleteLikeCompany() {
//		StudentUserDao studentUserDao = new StudentUserDao();
//		StudentUser user = studentUserDao.retrieveUser("DuZhouzhou");
//		user.getCompanys().remove(0);
//		studentUserDao.updateUser(user);
//	}
//
//	private void deleteLikeEvent() {
//		StudentUserDao studentUserDao = new StudentUserDao();
//		StudentUser user = studentUserDao.retrieveUser("DuZhouzhou");
//		user.getEvents().remove(0);
//		studentUserDao.updateUser(user);
//	}
	
	private void addInterestedArea() {
		StudentUserDao studentUserDao = new StudentUserDao();
		CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
		StudentUser user = studentUserDao.retrieveUser("DuZhouzhou");
		List<CompanyCategory> areas = new ArrayList<CompanyCategory>();
		areas.add(companyCategoryDao.retrieveCompanyCategory(2L));
		user.setInterestedArea(areas);
		studentUserDao.updateUser(user);
	}

	private void addLikeEvent() {
		StudentUserDao studentUserDao = new StudentUserDao();
		EventDao eventDao = new EventDao();
		StudentUser user = studentUserDao.retrieveUser("DuZhouzhou");
		List<Event> events = new ArrayList<Event>();
		events.add(eventDao.retrieveEvent(1L));
		user.setEvents(events);
		studentUserDao.updateUser(user);
	}

	private void addLikeCompany() {
		StudentUserDao studentUserDao = new StudentUserDao();
		CompanyDao companyDao = new CompanyDao();
		StudentUser user = studentUserDao.retrieveUser("DuZhouzhou");
		List<Company> companys = new ArrayList<Company>();
		companys.add(companyDao.retrieveCompany(1L));
		companys.add(companyDao.retrieveCompany(2L));
		user.setCompanys(companys);
		studentUserDao.updateUser(user);
	}

	private void createStudentUser() {
		Log.debug("createStudentUser()");
		StudentUserDao studentUserDao = new StudentUserDao();
		StudentUser student1 = new StudentUser();
		student1.setLogin("DuZhouzhou");
		String password = "N0More$ecret";
		String salt = Security.randomCharString();
		String hash = Security.sha256(salt + password);
		student1.setPassword(hash);
		student1.setSalt(salt);
		student1.setFirstName("Zhouzhou");
		student1.setLastName("Du");
		student1.setGender("Male");
		student1.setEmail("john_1990@163.com");
		studentUserDao.createUser(student1);
	}

}

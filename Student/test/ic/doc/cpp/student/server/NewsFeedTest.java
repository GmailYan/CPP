package ic.doc.cpp.student.server;

import ic.doc.cpp.student.server.dao.CompanyDao;
import ic.doc.cpp.student.server.dao.EventCategoryDao;
import ic.doc.cpp.student.server.dao.EventDao;
import ic.doc.cpp.student.server.domain.Event;

import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class NewsFeedTest {

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
	public void testEventCategoryDao() {
		try {
			createTestEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createTestEvent() {
		Date currentDate = new Date();
		
		EventDao eventDao = new EventDao();
		EventCategoryDao eventCategoryDao = new EventCategoryDao();
		CompanyDao companyDao = new CompanyDao();
		Event event = new Event();
		event.setTitle("Microsoft Summer Internship");
		event.setCategory(eventCategoryDao.retrieveEventCategory(2L));
		event.setCompany(companyDao.retrieveCompany(1L));
		event.setPicture("companyLogo/microsoft.jpg");
		event.setCreatedTimestamp(currentDate);
		event.setUpdatedTimestamp(currentDate);
		event.setDescription("A good internship.");
		event.setStart_date(currentDate);
		event.setEnd_date(currentDate);
		event.setWebsite("www.test.com");
		eventDao.createEvent(event);
	}

}

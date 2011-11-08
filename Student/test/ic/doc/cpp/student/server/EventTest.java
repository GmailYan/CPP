package ic.doc.cpp.student.server;

import java.util.List;

import ic.doc.cpp.student.server.dao.CompanyDao;
import ic.doc.cpp.student.server.dao.EventCategoryDao;
import ic.doc.cpp.student.server.dao.EventDao;
import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.Event;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class EventTest {

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
			createEvent();
			deleteEvent();
			showCompanyEvents();
//			deleteCompanyAffectEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showCompanyEvents() {
		CompanyDao companyDao = new CompanyDao();
		Company company = companyDao.retrieveCompany(1L);
		List<Event> events = company.getEvents();
		for (Event e : events) {
			Log.debug(e.toString());
		}
	}

	private void deleteEvent() {
		Log.debug("Testing deleteEvent()....");
		EventDao eventDao = new EventDao();
		Event event = eventDao.retrieveEvent(4L);
		if (event != null)
			eventDao.deleteEvent(event);
	}

	private void deleteCompanyAffectEvent() {
		CompanyDao companyDao = new CompanyDao();
		Company company = companyDao.retrieveCompany(1L);
		companyDao.deleteCompany(company);
	}

	private void createEvent() {
		EventDao eventDao = new EventDao();
		EventCategoryDao eventCategoryDao = new EventCategoryDao();
		CompanyDao companyDao = new CompanyDao();
		Event event = new Event();
		event.setTitle("ABC presentation");
		event.setCategory(eventCategoryDao.retrieveEventCategory(4L));
		event.setCompany(companyDao.retrieveCompany(2L));
		eventDao.createEvent(event);
		
		Event event1 = new Event();
		event1.setTitle("MS Summer Internship");
		event1.setCategory(eventCategoryDao.retrieveEventCategory(2L));
		event1.setCompany(companyDao.retrieveCompany(1L));
		eventDao.createEvent(event1);
		
		Event event2 = new Event();
		event2.setTitle("MS Spring Internship");
		event2.setCategory(eventCategoryDao.retrieveEventCategory(3L));
		event2.setCompany(companyDao.retrieveCompany(1L));
		eventDao.createEvent(event2);
		
		Event event3 = new Event();
		event3.setTitle("Test Deleting Event");
		event3.setCategory(eventCategoryDao.retrieveEventCategory(3L));
		event3.setCompany(companyDao.retrieveCompany(1L));
		eventDao.createEvent(event3);
		
		
	}

}

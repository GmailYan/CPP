package ic.doc.cpp.student.server;

import ic.doc.cpp.student.server.dao.EventCategoryDao;
import ic.doc.cpp.student.server.domain.EventCategory;

import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class EventCategoryTest {

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
			createEventCategory();
			updateEventCategory();
			deleteEventCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteEventCategory() {
		    
		EventCategoryDao categoryDao = new EventCategoryDao();
		
		categoryDao.deleteEventCategory(categoryDao.retrieveEventCategory(6L));
		
		List<EventCategory> categorys = categoryDao.retrieveEventCategorys();
		
		for (EventCategory category : categorys) {
		  Log.debug(category.toString());
		}  
		
	}

	private void updateEventCategory() {
	    
		EventCategoryDao categoryDao = new EventCategoryDao();
	    
		EventCategory category = categoryDao.retrieveEventCategory(1L);
		category.setCategoryName("All/Internship");
	    categoryDao.updateEventCategory(category);
	    
	    List<EventCategory> categorys = categoryDao.retrieveEventCategorys();
	    
	    for (EventCategory cat : categorys) {
	      Log.debug(cat.toString());
	    }  
	}

	private void createEventCategory() {
	    
		EventCategoryDao categoryDao = new EventCategoryDao();

		EventCategory category2 = new EventCategory();
	    category2.setCategoryName("All/Internshi");
	    category2.setParentId(0L);
	    Long category2Id = categoryDao.createEventCategory(category2);
	    
	    EventCategory category3 = new EventCategory();
	    category3.setCategoryName("All/Internship/Summer Internship");
	    category3.setParentId(category2Id);
	    categoryDao.createEventCategory(category3);
	    
	    EventCategory category4 = new EventCategory();
	    category4.setCategoryName("All/Internship/Spring Internship");
	    category4.setParentId(category2Id);
	    categoryDao.createEventCategory(category4);
	    
	    EventCategory category5 = new EventCategory();
	    category5.setCategoryName("All/Presentation");
	    category5.setParentId(0L);
	    categoryDao.createEventCategory(category5);

	    EventCategory category6 = new EventCategory();
	    category6.setCategoryName("All/Career Fair");
	    category6.setParentId(0L);
	    categoryDao.createEventCategory(category6);
	    
	    EventCategory category7 = new EventCategory();
	    category7.setCategoryName("Test");
	    category7.setParentId(0L);
	    categoryDao.createEventCategory(category7);

	    List<EventCategory> categorys = categoryDao.retrieveEventCategorys();
	    
	    for (EventCategory categroy : categorys) {
	      Log.debug(categroy.toString());
	    }
	}

}

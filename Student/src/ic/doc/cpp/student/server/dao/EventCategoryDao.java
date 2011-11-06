package ic.doc.cpp.student.server.dao;

import ic.doc.cpp.student.server.domain.EventCategory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class EventCategoryDao extends BaseDao {
	
	public Long createEventCategory(EventCategory eventCategory) {
		
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Long categoryId = -1L;
		
		try {
			tx.begin();
			em.persist(eventCategory);
			categoryId = eventCategory.getCategoryId();
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return categoryId;
			  
	}
	
	public EventCategory retrieveEventCategory(Long categoryId) {
		EntityManager em = createEntityManager();
		EventCategory eventCategory = null;

		try {
			TypedQuery<EventCategory> query = em.createQuery("select a from EventCategory a where a.categoryId = ?1", EventCategory.class);
			query.setParameter(1, categoryId);
			eventCategory = query.getSingleResult();
		} finally {
			em.close();
		}
		return eventCategory;
	}
	
	public List<EventCategory> retrieveEventCategorys() {
		EntityManager em = createEntityManager();
		List<EventCategory> list = null;
		
		try {
			TypedQuery<EventCategory> query = em.createQuery("select a from EventCategory a", EventCategory.class);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
	
	public EventCategory updateEventCategory(EventCategory eventCategory) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		EventCategory eventCategory2 = null;
		
		try {
			tx.begin();
			eventCategory2 = em.merge(eventCategory);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return eventCategory2;
	}
	
	public void deleteEventCategory(EventCategory eventCategory) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.merge(eventCategory));
			tx.commit();
		} catch (Throwable t){
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}

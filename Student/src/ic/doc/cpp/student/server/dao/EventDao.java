package ic.doc.cpp.student.server.dao;

import ic.doc.cpp.student.server.domain.Event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class EventDao extends BaseDao {
	public Long createEvent(Event event) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Long eventId = -1L;
		
		try {
			tx.begin();
			em.persist(event);
			eventId = event.getEventId();
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return eventId;
	}
	
	public Event retrieveEvent(Long eventId) {
		EntityManager em = createEntityManager();
		Event event = null;

		try {
			TypedQuery<Event> query = em.createQuery("select a from Event a where a.id = ?1", Event.class);
			query.setParameter(1, eventId);
			event = query.getSingleResult();
		} finally {
			em.close();
		}
		return event;
	}
	
	public List<Event> retrieveEvents() {
		EntityManager em = createEntityManager();
		List<Event> list = null;
		
		try {
			TypedQuery<Event> query = em.createQuery("select a from Event a", Event.class);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
	
	public Event updateEvent(Event event) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Event event2 = null;
		
		try {
			tx.begin();
			event2 = em.merge(event);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return event2;
	}
	
	public void deleteEvent(Event event) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.merge(event));
			tx.commit();
		} catch (Throwable t){
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}

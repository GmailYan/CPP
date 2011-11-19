package ic.doc.cpp.student.server.dao;

import ic.doc.cpp.student.server.domain.Event;
import ic.doc.cpp.student.server.domain.EventCategory;
import ic.doc.cpp.student.server.domain.EventCategory_;
import ic.doc.cpp.student.server.domain.Event_;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<Event> cq = qb.createQuery(Event.class);
			Root<Event> e = cq.from(Event.class);
			Predicate condition = qb.equal(e.get(Event_.eventId), eventId);
			cq.where(condition);
			TypedQuery<Event> query = em.createQuery(cq);
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
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Event> cq = cb.createQuery(Event.class);
			cq.from(Event.class);
			TypedQuery<Event> query = em.createQuery(cq);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
	
	public List<Event> retrieveEvents(String categoryName) {
		EntityManager em = createEntityManager();
		List<Event> events = null;
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Event> cq = cb.createQuery(Event.class);
			Root<Event> e = cq.from(Event.class);
			Path<EventCategory> c = e.get(Event_.category);
			Predicate condition = cb.like(c.get(EventCategory_.categoryName), categoryName+"%");
			cq.where(condition);
			TypedQuery<Event> query = em.createQuery(cq);
			events = query.getResultList();
		} finally {
			em.close();
		}
		
		return events;
	}
	
	public List<Event> retrieveEvents(String categoryName, Date updateTime) {
		EntityManager em = createEntityManager();
		List<Event> events = null;
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Event> cq = cb.createQuery(Event.class);
			Root<Event> e = cq.from(Event.class);
			Path<EventCategory> c = e.get(Event_.category);
			Predicate condition = cb.like(c.get(EventCategory_.categoryName), categoryName+"%");
			Predicate updateTimecondition = cb.greaterThan(e.get(Event_.updatedTimestamp), updateTime);
			cq.where(cb.and(condition, updateTimecondition));
			TypedQuery<Event> query = em.createQuery(cq);
			events = query.getResultList();
		} finally {
			em.close();
		}
		
		return events;
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

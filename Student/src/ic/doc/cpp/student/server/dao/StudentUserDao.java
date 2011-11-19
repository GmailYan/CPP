package ic.doc.cpp.student.server.dao;

import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.domain.StudentUser_;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class StudentUserDao extends BaseDao{
	public String createUser(StudentUser user) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		String login = "";
		
		try {
			tx.begin();
			em.persist(user);
			login = user.getLogin();
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return login;
	}
	
	public StudentUser updateUser(StudentUser student) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudentUser student2 = null;
		
		try {
			tx.begin();
			student2 = em.merge(student);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return student2;
		
	}
	
	public StudentUser retrieveUser(String login) {
		EntityManager em = createEntityManager();
		StudentUser user = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<StudentUser> cq = cb.createQuery(StudentUser.class);
			Root<StudentUser> s = cq.from(StudentUser.class);
			cq.where(cb.equal(s.get(StudentUser_.login), login));
			TypedQuery<StudentUser> query = em.createQuery(cq);
			user = query.getSingleResult();
		} finally {
			em.close();
		}
		return user;
	}
}

package ic.doc.cpp.student.server.dao;

import ic.doc.cpp.student.server.domain.StudentUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
			TypedQuery<StudentUser> query = em.createQuery("select a from StudentUser a where a.login = ?1", StudentUser.class);
			query.setParameter(1, login);
			user = query.getSingleResult();
		} finally {
			em.close();
		}
		return user;
	}
}

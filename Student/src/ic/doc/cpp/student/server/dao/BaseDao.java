package ic.doc.cpp.student.server.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseDao {
	private static final EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("ic.doc.cpp");

	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}

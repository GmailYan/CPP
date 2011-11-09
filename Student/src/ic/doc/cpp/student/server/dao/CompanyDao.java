package ic.doc.cpp.student.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ic.doc.cpp.student.server.domain.Company;

public class CompanyDao extends BaseDao {
	
	public Long createCompany(Company company) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Long companyId = -1L;
		
		try {
			tx.begin();
			em.persist(company);
			companyId = company.getCompanyId();
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return companyId;
	}
	
	public Company retrieveCompany(Long companyId) {
		EntityManager em = createEntityManager();
		Company company = null;

		try {
			TypedQuery<Company> query = em.createQuery("select a from Company a where a.id = ?1", Company.class);
			query.setParameter(1, companyId);
			company = query.getSingleResult();
		} finally {
			em.close();
		}
		return company;
	}
	
	public List<Company> retrieveCompanys() {
		EntityManager em = createEntityManager();
		List<Company> list = null;
		
		try {
			TypedQuery<Company> query = em.createQuery("select a from Company a", Company.class);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
	
	public Company updateCompany(Company company) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Company company2 = null;
		
		try {
			tx.begin();
			company2 = em.merge(company);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return company2;
	}
	
	public void deleteCompany(Company company) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.merge(company));
			tx.commit();
		} catch (Throwable t){
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}

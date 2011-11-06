package ic.doc.cpp.student.server.dao;

import java.util.List;

import ic.doc.cpp.student.server.domain.CompanyCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CompanyCategoryDao extends BaseDao{
	
	public Long createCompanyCategory(CompanyCategory companyCategory) {
		
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Long categoryId = -1L;
		
		try {
			tx.begin();
			em.persist(companyCategory);
			categoryId = companyCategory.getCategoryId();
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return categoryId;
			  
	}
	
	public CompanyCategory retrieveCompanyCategory(Long categoryId) {
		EntityManager em = createEntityManager();
		CompanyCategory companyCategory = null;

		try {
			TypedQuery<CompanyCategory> query = em.createQuery("select a from CompanyCategory a where a.categoryId = ?1", CompanyCategory.class);
			query.setParameter(1, categoryId);
			companyCategory = query.getSingleResult();
		} finally {
			em.close();
		}
		return companyCategory;
	}
	
	public List<CompanyCategory> retrieveCompanyCategorys() {
		EntityManager em = createEntityManager();
		List<CompanyCategory> list = null;
		
		try {
			TypedQuery<CompanyCategory> query = em.createQuery("select a from CompanyCategory a", CompanyCategory.class);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
	
	public CompanyCategory updateCompanyCategory(CompanyCategory companyCategory) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		CompanyCategory companyCategory2 = null;
		
		try {
			tx.begin();
			companyCategory2 = em.merge(companyCategory);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return companyCategory2;
	}
	
	public void deleteCompanyCategory(CompanyCategory companyCategory) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.merge(companyCategory));
			tx.commit();
		} catch (Throwable t){
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

}

package ic.doc.cpp.student.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.CompanyCategory;
import ic.doc.cpp.student.server.domain.CompanyCategory_;
import ic.doc.cpp.student.server.domain.Company_;

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
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Company> cq = cb.createQuery(Company.class);
			Root<Company> c = cq.from(Company.class);
			Predicate condition = cb.equal(c.get(Company_.companyId), companyId);
			cq.where(condition);
			TypedQuery<Company> query = em.createQuery(cq);
			company = query.getSingleResult();
		} finally {
			em.close();
		}
		return company;
	}
	
	public List<Company> retrieveCompanys(String name, String category) {
		EntityManager em = createEntityManager();
		List<Company> company = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Company> cq = cb.createQuery(Company.class);
			Root<Company> c = cq.from(Company.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			if (name != null) {
				Predicate nameCondition = cb.like(c.get(Company_.name), "%"+name+"%");
				conditions.add(nameCondition);
			}
			if (category != null) {
				Path<CompanyCategory> companyCategoryPath = c.get(Company_.category);
				Path<String> companyCategoryNamePath = companyCategoryPath.get(CompanyCategory_.categoryName);
				Predicate categoryCondition = cb.like(companyCategoryNamePath, category+"%");
				conditions.add(categoryCondition);
			}
			cq.where(conditions.toArray(new Predicate[0]));
			TypedQuery<Company> query = em.createQuery(cq);
			company = query.getResultList();
		} finally {
			em.close();
		}
		
		return company;
	}
	
	public List<Company> retrieveCompanys() {
		EntityManager em = createEntityManager();
		List<Company> list = null;
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Company> c = cb.createQuery(Company.class);
			c.from(Company.class);
			TypedQuery<Company> query = em.createQuery(c);
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

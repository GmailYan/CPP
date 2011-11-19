package ic.doc.cpp.student.shared.criteria;

import org.hibernate.Criteria;

public interface PersistentEntityCriteria {
	void apply(Criteria targetCriteria);
}

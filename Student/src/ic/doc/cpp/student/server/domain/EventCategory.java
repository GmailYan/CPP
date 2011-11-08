package ic.doc.cpp.student.server.domain;

import ic.doc.cpp.student.shared.dto.EventCategoryDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_category")
public class EventCategory {
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long categoryId;
	
	@Column(name = "category_name", length = 100, nullable = false)
	protected String categoryName;
	
	@Column(name = "parent_id", nullable = false)
	protected Long parentId;
	
	public EventCategory() {}
	
	public EventCategory(Long categoryID) {
		this.categoryId = categoryID;
	}
	
	public EventCategory(EventCategoryDto eventCategory) {
		setCategoryName(eventCategory.getCategoryName());
		setParentId(eventCategory.getParentId());
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Category Id: ").append(getCategoryId()).append("\n");
		sb.append("Category Name: ").append(getCategoryName()).append("\n");
		sb.append("Parent Name: ").append(getParentId());
		
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EventCategory))
			return false;
		EventCategory other = (EventCategory) obj;

		if (categoryId == null) {
    		if (other.categoryId != null)
    			return false;
    	} else if (!categoryId.equals(other.categoryId)) {
    		return false;
    	}
    	
    	if (categoryName == null) {
    		if (other.categoryName != null)
	    			return false;
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
	    
    	if (parentId == null) {
	    	if (other.parentId != null)
	    		return false;
	    } else if (!parentId.equals(other.parentId)) {
	    	return false;
	    }
	    
	    return true;
	}

}

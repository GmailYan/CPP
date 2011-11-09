package ic.doc.cpp.student.shared.dto;

import java.io.Serializable;

public class CompanyCategoryDto implements Serializable {
	private static final long serialVersionUID = 7749630501976551263L;
	 
	protected Long categoryId;
	protected String categoryName;
	protected Long parentId;
	
	public CompanyCategoryDto() {
		categoryId = -1L;
	}
	
	public CompanyCategoryDto(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public CompanyCategoryDto(Long categoryId, String categoryName,
			Long parentId) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.parentId = parentId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		if (!(obj instanceof CompanyCategoryDto))
			return false;
		CompanyCategoryDto other = (CompanyCategoryDto) obj;

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

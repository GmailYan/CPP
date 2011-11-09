package ic.doc.cpp.student.server.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name = "companys")
public class Company {
	@Id
	@Column(name = "company_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long companyId;
	
	@Column(name = "name", length = 200, nullable = false)
	protected String name;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    protected CompanyCategory category;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
    protected List<Event> events;
    
	@Column(name = "description", length = 500)
	protected String description;
	
	@Column(name = "website", length = 200)
	protected String website;
	
	@Column(name = "logo", length = 100)
	protected String logo;
	
	public Company() {}
	
	public Company(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public CompanyCategory getCategory() {
		return category;
	}

	public void setCategory(CompanyCategory category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(companyId).append(", ");
		sb.append("Name: ").append(name).append(", ");
		sb.append("Category: ").append(category.categoryName).append(", ");

		return sb.toString();
	}

	public List<Event> getEvents() {
		return events;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId.hashCode();
		result = prime * result + name.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Company))
			return false;
		Company other = (Company) obj;

		if (companyId == null) {
    		if (other.companyId != null)
    			return false;
    	} else if (!companyId.equals(other.companyId)) {
    		return false;
    	}
    	
    	if (name == null) {
    		if (other.name != null)
	    			return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
	    
    	if (category == null) {
	    	if (other.category != null)
	    		return false;
	    } else if (!category.equals(other.category)) {
	    	return false;
	    }
	    
	    return true;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}

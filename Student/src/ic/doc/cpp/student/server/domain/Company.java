package ic.doc.cpp.student.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "companys")
public class Company {
	@Id
	@Column(name = "company_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long companyId;
	
	@Column(name = "name", length = 200)
	protected String name;
	
	@Column(name = "category_id")
	protected Long categoryId;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName="category_id")
    protected CompanyCategory category;
    
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

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	
}

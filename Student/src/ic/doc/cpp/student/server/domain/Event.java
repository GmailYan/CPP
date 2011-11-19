package ic.doc.cpp.student.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event extends BaseEntity{
	@Id
	@Column(name = "event_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long eventId;
	
	@Column(name = "title", length = 200)
	protected String title;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id", referencedColumnName="category_id", nullable = false)
	protected EventCategory category;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "company_id", referencedColumnName="company_id", nullable = false)
	protected Company company;
	
	@Column(name = "description")
	protected String description;
	
	@Column(name = "website")
	protected String website;
	
	@Column(name = "start_date")
	protected Date start_date;
	
	@Column(name = "end_date")
	protected Date end_date;
	
	@Column(name = "picture")
	protected String picture;
	
	public Event() {}
	
	public Long getEventId() {
		return eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EventCategory getCategory() {
		return category;
	}

	public void setCategory(EventCategory category) {
		this.category = category;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("Id: ").append(eventId).append(", ");
		sb.append("Title: ").append(title).append(", ");
		sb.append("Category: ").append(category.categoryName).append(";");
		sb.append("Company: ").append(company.toString()).append(";");

		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId.hashCode();
		result = prime * result + title.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;

		if (eventId == null) {
    		if (other.eventId != null)
    			return false;
    	} else if (!eventId.equals(other.eventId)) {
    		return false;
    	}
    	
    	if (title == null) {
    		if (other.title != null)
	    			return false;
		} else if (!title.equals(other.title)) {
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
	
}

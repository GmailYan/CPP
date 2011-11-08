package ic.doc.cpp.student.shared.dto;

import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.EventCategory;

import java.io.Serializable;
import java.sql.Date;

public class EventDto implements Serializable{
	
	private static final long serialVersionUID = -3434148714982575460L;
	
	protected Long eventId;
	protected String title;
	protected EventCategory category;
	protected Company company;
	protected String description;
	protected String website;
	protected Date start_date;
	protected Date end_date;
	protected String picture;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
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
		sb.append("Id: ").append(eventId).append(", ");
		sb.append("Title: ").append(title).append(", ");
		sb.append("Category: ").append(category.getCategoryName()).append(";");
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
		if (!(obj instanceof EventDto))
			return false;
		EventDto other = (EventDto) obj;

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

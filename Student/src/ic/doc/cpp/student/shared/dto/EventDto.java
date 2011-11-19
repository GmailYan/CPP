package ic.doc.cpp.student.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class EventDto implements Serializable{
	
	private static final long serialVersionUID = 6072601952142896135L;
	
	protected Long eventId;
	protected String title;
	protected Long categoryId;
	protected Long companyId;
	protected String description;
	protected String website;
	protected Date start_date;
	protected Date end_date;
	protected String picture;
	
	public EventDto() {}

	public EventDto(Long eventId, String title, Long categoryId,
			Long companyId, String description, String website,
			Date start_date, Date end_date, String picture) {
		super();
		this.eventId = eventId;
		this.title = title;
		this.categoryId = categoryId;
		this.companyId = companyId;
		this.description = description;
		this.website = website;
		this.start_date = start_date;
		this.end_date = end_date;
		this.picture = picture;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

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

	public String getPicture() {
		return picture;
	}
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(eventId).append(", ");
		sb.append("Title: ").append(title).append(", ");
		sb.append("Category: ").append(categoryId).append(";");
		sb.append("Company: ").append(companyId.toString()).append(";");
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
	    
    	if (categoryId == null) {
	    	if (other.categoryId != null)
	    		return false;
	    } else if (!categoryId.equals(other.categoryId)) {
	    	return false;
	    }
    	if (companyId == null) {
    		if (other.companyId != null)
    			return false;
    	} else if (!companyId.equals(other.companyId)) {
    		return false;
    	}
    	
    	if (description == null) {
    		if (other.description != null)
    			return false;
    	} else if (!description.equals(other.description)) {
    		return false;
    	}

    	if (website == null) {
    		if (other.website != null)
    			return false;
    	} else if (!website.equals(other.website)) {
    		return false;
    	}
    	if (start_date == null) {
    		if (other.start_date != null)
    			return false;
    	} else if (!start_date.equals(other.start_date)) {
    		return false;
    	}
    	
    	if (end_date == null) {
    		if (other.end_date != null)
    			return false;
    	} else if (!end_date.equals(other.end_date)) {
    		return false;
    	}
    	
    	if (picture == null) {
    		if (other.picture != null)
    			return false;
    	} else if (!picture.equals(other.picture)) {
    		return false;
    	}
	    
	    return true;
	}

}

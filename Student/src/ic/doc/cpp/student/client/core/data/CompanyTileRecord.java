package ic.doc.cpp.student.client.core.data;

import ic.doc.cpp.student.shared.dto.EventDto;

import java.util.List;

import com.smartgwt.client.widgets.tile.TileRecord;

public class CompanyTileRecord extends TileRecord {
	private List<EventDto> events;
	
	public CompanyTileRecord(Long companyId, String name, Long companyCategoryId, 
			String category, String website, String description, 
			String logo, List<EventDto> events) {
		setAttribute("companyId", companyId);
		setAttribute("name", name);
		setAttribute("companyCategoryId", companyCategoryId);
		setAttribute("category", category);
		setAttribute("website", website);
		setAttribute("description", description);
		setAttribute("logo", logo);
		this.events = events;
	}
	
	public List<EventDto> getEvents() {
		return events;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CompanyTileRecord))
			return false;
		CompanyTileRecord other = (CompanyTileRecord) obj;

		if (getAttributeAsLong("companyId") == null) {
    		if (other.getAttributeAsLong("companyId") != null)
    			return false;
    	} else if (!getAttributeAsLong("companyId").equals(other.getAttributeAsLong("companyId"))) {
    		return false;
    	}
    	
    	if (getAttribute("name") == null) {
    		if (other.getAttribute("name") != null)
	    			return false;
		} else if (!getAttribute("name").equals(other.getAttribute("name"))) {
			return false;
		}
	    
    	if (getAttributeAsLong("categoryId") == null) {
	    	if (other.getAttributeAsLong("categoryId") != null)
	    		return false;
	    } else if (!getAttributeAsLong("categoryId").equals(other.getAttributeAsLong("categoryId"))) {
	    	return false;
	    }
    	
    	if (getAttribute("website") == null) {
	    	if (other.getAttribute("website") != null)
	    		return false;
	    } else if (!getAttribute("website").equals(other.getAttribute("website"))) {
	    	return false;
	    }
    	
    	if (getAttribute("description") == null) {
	    	if (other.getAttributeAsLong("description") != null)
	    		return false;
	    } else if (!getAttribute("description").equals(other.getAttribute("description"))) {
	    	return false;
	    }
    	
    	if (getAttribute("logo") == null) {
	    	if (other.getAttribute("logo") != null)
	    		return false;
	    } else if (!getAttribute("logo").equals(other.getAttribute("logo"))) {
	    	return false;
	    }
	    
	    return true;
	}
	
	
	
}

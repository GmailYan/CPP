package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryIdResult;
import java.lang.Long;
import java.util.Date;

public class RetrieveEventsUsingCategoryId extends
		ActionImpl<RetrieveEventsUsingCategoryIdResult> {

	private Long id;
	private Date upDateTime;

	@SuppressWarnings("unused")
	private RetrieveEventsUsingCategoryId() {
		// For serialization only
	}

	public RetrieveEventsUsingCategoryId(Long id, Date upDateTime) {
		this.id = id;
		this.upDateTime = upDateTime;
	}

	public Date getUpdateTime() {
		return upDateTime;
	}
	
	public Long getId() {
		return id;
	}
}

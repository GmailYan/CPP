package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.AddStudentInterestedEventResult;
import java.lang.Long;

public class AddStudentInterestedEvent extends
		ActionImpl<AddStudentInterestedEventResult> {

	private Long eventId;

	@SuppressWarnings("unused")
	private AddStudentInterestedEvent() {
		// For serialization only
	}

	public AddStudentInterestedEvent(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEventId() {
		return eventId;
	}
}

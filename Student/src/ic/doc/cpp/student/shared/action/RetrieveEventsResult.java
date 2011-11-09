package ic.doc.cpp.student.shared.action;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import ic.doc.cpp.student.shared.dto.EventDto;

public class RetrieveEventsResult implements Result {

	private List<EventDto> events;

	@SuppressWarnings("unused")
	private RetrieveEventsResult() {
		// For serialization only
	}

	public RetrieveEventsResult(List<EventDto> events) {
		this.events = events;
	}

	public List<EventDto> getEvents() {
		return events;
	}
}

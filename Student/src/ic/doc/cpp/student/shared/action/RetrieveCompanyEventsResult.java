package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.student.shared.dto.EventDto;

import java.util.List;

public class RetrieveCompanyEventsResult implements Result {

	private List<EventDto> eventDtos;

	@SuppressWarnings("unused")
	private RetrieveCompanyEventsResult() {
		// For serialization only
	}

	public RetrieveCompanyEventsResult(List<EventDto> eventDtos) {
		this.eventDtos = eventDtos;
	}

	public List<EventDto> getEventDtos() {
		return eventDtos;
	}
}

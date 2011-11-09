package ic.doc.cpp.student.server.handler;

import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.EventDao;
import ic.doc.cpp.student.server.domain.Event;
import ic.doc.cpp.student.shared.action.RetrieveEvents;
import ic.doc.cpp.student.shared.action.RetrieveEventsResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveEventsActionHandler implements
		ActionHandler<RetrieveEvents, RetrieveEventsResult> {

	@Inject
	public RetrieveEventsActionHandler() {
	}

	@Override
	public RetrieveEventsResult execute(RetrieveEvents action,
			ExecutionContext context) throws ActionException {
		RetrieveEventsResult result = null;
		
		try {
			EventDao eventDao = new EventDao();
			List<Event> events = eventDao.retrieveEvents();
			if (events != null)
				result = new RetrieveEventsResult(CreateDto.createEventDtos(events));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RetrieveEvents action, RetrieveEventsResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RetrieveEvents> getActionType() {
		return RetrieveEvents.class;
	}
}

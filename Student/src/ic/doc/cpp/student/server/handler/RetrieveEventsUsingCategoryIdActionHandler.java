package ic.doc.cpp.student.server.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.EventCategoryDao;
import ic.doc.cpp.student.server.dao.EventDao;
import ic.doc.cpp.student.server.domain.Event;
import ic.doc.cpp.student.server.domain.EventCategory;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.GetEntityThroughDao;
import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryId;
import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryIdResult;
import ic.doc.cpp.student.shared.dto.EventDto;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveEventsUsingCategoryIdActionHandler
		implements
		ActionHandler<RetrieveEventsUsingCategoryId, RetrieveEventsUsingCategoryIdResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public RetrieveEventsUsingCategoryIdActionHandler(
			final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public RetrieveEventsUsingCategoryIdResult execute(
			RetrieveEventsUsingCategoryId action, ExecutionContext context)
			throws ActionException {
		RetrieveEventsUsingCategoryIdResult result = null;
		
		try {
			StudentUser currentUser = GetEntityThroughDao.getStudentUser(provider);
			Long categoryId = action.getId();
			EventCategoryDao eventCategoryDao = new EventCategoryDao();
			EventCategory eventCategory = eventCategoryDao.retrieveEventCategory(categoryId);
			String categoryName = eventCategory.getCategoryName();
			Date updateTime = action.getUpdateTime();
			EventDao eventDao = new EventDao();
			List<Event> events = eventDao.retrieveEvents(categoryName, updateTime);
			List<EventDto> eventDtos = CreateDto.createEventDtos(events);
			checkIfLikedByCurrentUser(currentUser,eventDtos);
			result = new RetrieveEventsUsingCategoryIdResult(categoryId, eventDtos);
		} catch(Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	private void checkIfLikedByCurrentUser(StudentUser currentUser,
			List<EventDto> eventDtos) {
		List<Event> interestedEvents = currentUser.getEvents();
		
		Map<Long, Event> eventMap = new HashMap<Long, Event>();
		
		for (Event e : interestedEvents) {
			eventMap.put(e.getEventId(), e);
		}
		
		for (EventDto eventDto : eventDtos) {
			if (eventMap.get(eventDto.getEventId()) != null) {
				eventDto.setLikedByCurrentUser(true);
			}
		}
	}

	@Override
	public void undo(RetrieveEventsUsingCategoryId action,
			RetrieveEventsUsingCategoryIdResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveEventsUsingCategoryId> getActionType() {
		return RetrieveEventsUsingCategoryId.class;
	}
}

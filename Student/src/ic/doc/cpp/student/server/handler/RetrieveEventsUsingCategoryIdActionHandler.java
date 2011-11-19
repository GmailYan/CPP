package ic.doc.cpp.student.server.handler;

import java.util.Date;
import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.EventCategoryDao;
import ic.doc.cpp.student.server.dao.EventDao;
import ic.doc.cpp.student.server.domain.Event;
import ic.doc.cpp.student.server.domain.EventCategory;
import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryId;
import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryIdResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveEventsUsingCategoryIdActionHandler
		implements
		ActionHandler<RetrieveEventsUsingCategoryId, RetrieveEventsUsingCategoryIdResult> {

	@Inject
	public RetrieveEventsUsingCategoryIdActionHandler() {
	}

	@Override
	public RetrieveEventsUsingCategoryIdResult execute(
			RetrieveEventsUsingCategoryId action, ExecutionContext context)
			throws ActionException {
		RetrieveEventsUsingCategoryIdResult result = null;
		
		try {
			Long categoryId = action.getId();
			EventCategoryDao eventCategoryDao = new EventCategoryDao();
			EventCategory eventCategory = eventCategoryDao.retrieveEventCategory(categoryId);
			String categoryName = eventCategory.getCategoryName();
			Date updateTime = action.getUpdateTime();
			EventDao eventDao = new EventDao();
			List<Event> events = eventDao.retrieveEvents(categoryName, updateTime);
			result = new RetrieveEventsUsingCategoryIdResult(categoryId, CreateDto.createEventDtos(events));
		} catch(Exception e) {
			throw new ActionException(e);
		}
		
		return result;
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

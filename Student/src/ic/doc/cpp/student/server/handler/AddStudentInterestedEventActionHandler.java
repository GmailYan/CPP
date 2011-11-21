package ic.doc.cpp.student.server.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.Event;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.GetEntityThroughDao;
import ic.doc.cpp.student.shared.action.AddStudentInterestedEvent;
import ic.doc.cpp.student.shared.action.AddStudentInterestedEventResult;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class AddStudentInterestedEventActionHandler
		implements
		ActionHandler<AddStudentInterestedEvent, AddStudentInterestedEventResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public AddStudentInterestedEventActionHandler(final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public AddStudentInterestedEventResult execute(
			AddStudentInterestedEvent action, ExecutionContext context)
			throws ActionException {
		AddStudentInterestedEventResult result = null;
		
		try {
			StudentUser student = GetEntityThroughDao.getStudentUser(provider);
			if (student != null) {
				List<Event> events = student.getEvents();
				if (!isDuplicatedInterestedEvent(events, action.getEventId())) {
					Event event = GetEntityThroughDao.getEvent(action.getEventId());
					events.add(event);
					StudentUserDao studentDao = new StudentUserDao();
					studentDao.updateUser(student);
					result = new AddStudentInterestedEventResult("Successfully add Event: " + event.getTitle());
				} else {
					result = new AddStudentInterestedEventResult("You've already add this event");
				}
			} else {
				throw new Exception("No such student!");
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}
	
	private boolean isDuplicatedInterestedEvent(
			List<Event> events, Long eventId) {
		boolean flag = false;
		for (Event evnet : events) {
			if (evnet.getEventId().equals(eventId)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void undo(AddStudentInterestedEvent action,
			AddStudentInterestedEventResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AddStudentInterestedEvent> getActionType() {
		return AddStudentInterestedEvent.class;
	}
}

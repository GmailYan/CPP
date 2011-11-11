package ic.doc.cpp.student.server.handler;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveCompanyEvents;
import ic.doc.cpp.student.shared.action.RetrieveCompanyEventsResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanyEventsActionHandler implements
		ActionHandler<RetrieveCompanyEvents, RetrieveCompanyEventsResult> {

	@Inject
	public RetrieveCompanyEventsActionHandler() {
	}

	@Override
	public RetrieveCompanyEventsResult execute(RetrieveCompanyEvents action,
			ExecutionContext context) throws ActionException {
		return null;
	}

	@Override
	public void undo(RetrieveCompanyEvents action,
			RetrieveCompanyEventsResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveCompanyEvents> getActionType() {
		return RetrieveCompanyEvents.class;
	}
}

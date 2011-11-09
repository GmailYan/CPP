package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.RetrieveEventsResult;

public class RetrieveEvents extends ActionImpl<RetrieveEventsResult> {

	public RetrieveEvents() {
	}
	
	@Override
	public boolean isSecured() {
		return true;
	}
}

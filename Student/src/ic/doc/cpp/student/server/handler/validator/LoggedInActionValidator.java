package ic.doc.cpp.student.server.handler.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.dispatch.shared.Result;

public class LoggedInActionValidator implements ActionValidator {

	private final Provider<HttpServletRequest> requestProvider;
	
	@Inject
	public LoggedInActionValidator(final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}
	
	@Override
	@Singleton
	public boolean isValid(Action<? extends Result> arg0)
			throws ActionException {
		boolean result = true;
		
		HttpSession session = requestProvider.get().getSession();
		Object authenticated = session.getAttribute("login.authenticated");
		if (authenticated == null)
			result = false;
		
		return result;
	}

}

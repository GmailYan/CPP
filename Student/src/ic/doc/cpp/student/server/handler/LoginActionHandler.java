package ic.doc.cpp.student.server.handler;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.Security;
import ic.doc.cpp.student.shared.action.Login;
import ic.doc.cpp.student.shared.action.LoginResult;
import ic.doc.cpp.student.shared.exception.LoginException;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.google.inject.Provider;

public class LoginActionHandler implements ActionHandler<Login, LoginResult> {
	
	private final Provider<HttpServletRequest> requestProvider;
	
	@Inject
	public LoginActionHandler(final Provider<HttpServletRequest> requestProvider) {
	    this.requestProvider = requestProvider;
	  }

	@Override
	public LoginResult execute(Login action, ExecutionContext context)
			throws ActionException {
		LoginResult result = null;
		StudentUserDao userDao = new StudentUserDao();
		
		try {
			StudentUser user = userDao.retrieveUser(action.getLogin());
			
			if (user != null && isValidLogin(action, user)) {
				HttpSession session = requestProvider.get().getSession();
				session.setAttribute("login.authenticated", action.getLogin());
				result = new LoginResult(session.getId());
			} else {
				throw new LoginException("Invalid user name or password.");
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}
	
	private boolean isValidLogin(Login action, StudentUser user) {
		String hash = Security.sha256(user.getSalt() + action.getPassword());
		return hash.equals(user.getPassword());
	}

	@Override
	public void undo(Login action, LoginResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<Login> getActionType() {
		return Login.class;
	}
}

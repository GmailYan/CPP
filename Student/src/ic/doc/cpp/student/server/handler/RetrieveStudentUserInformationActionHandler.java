package ic.doc.cpp.student.server.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.shared.action.RetrieveStudentUserInformation;
import ic.doc.cpp.student.shared.action.RetrieveStudentUserInformationResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveStudentUserInformationActionHandler
		implements
		ActionHandler<RetrieveStudentUserInformation, RetrieveStudentUserInformationResult> {

	private final Provider<HttpServletRequest> requestProvider;
	
	@Inject
	public RetrieveStudentUserInformationActionHandler(
			final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public RetrieveStudentUserInformationResult execute(
			RetrieveStudentUserInformation action, ExecutionContext context)
			throws ActionException {
		RetrieveStudentUserInformationResult result = null;

		try {
			HttpSession session = requestProvider.get().getSession();
			String login = session.getAttribute("login.authenticated").toString();
			StudentUserDao studentUserDao = new StudentUserDao();
			StudentUser studentUser = studentUserDao.retrieveUser(login);
			
			if (studentUser != null)
				result = new RetrieveStudentUserInformationResult(CreateDto.createStudentDto(studentUser)); 

		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveStudentUserInformation action,
			RetrieveStudentUserInformationResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveStudentUserInformation> getActionType() {
		return RetrieveStudentUserInformation.class;
	}
}

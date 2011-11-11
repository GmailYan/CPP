package ic.doc.cpp.student.server.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedCompanies;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedCompaniesResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveStudentInterestedCompaniesActionHandler
		implements
		ActionHandler<RetrieveStudentInterestedCompanies, RetrieveStudentInterestedCompaniesResult> {

	private final Provider<HttpServletRequest> request;
	
	@Inject
	public RetrieveStudentInterestedCompaniesActionHandler(
			final Provider<HttpServletRequest> request) {
		this.request = request;
	}

	@Override
	public RetrieveStudentInterestedCompaniesResult execute(
			RetrieveStudentInterestedCompanies action, ExecutionContext context)
			throws ActionException {
		RetrieveStudentInterestedCompaniesResult result = null;
		
		try {
			HttpSession session = request.get().getSession();
			String login = session.getAttribute("login.authenticated").toString();
			StudentUserDao studentDao = new StudentUserDao();
			StudentUser student = studentDao.retrieveUser(login);
			result = new RetrieveStudentInterestedCompaniesResult(
					CreateDto.createCompanyDtos(student.getCompanys()));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveStudentInterestedCompanies action,
			RetrieveStudentInterestedCompaniesResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RetrieveStudentInterestedCompanies> getActionType() {
		return RetrieveStudentInterestedCompanies.class;
	}
}

package ic.doc.cpp.student.server.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.GetEntityThroughDao;
import ic.doc.cpp.student.shared.action.AddStudentInterestedCompany;
import ic.doc.cpp.student.shared.action.AddStudentInterestedCompanyResult;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class AddStudentInterestedCompanyActionHandler
		implements
		ActionHandler<AddStudentInterestedCompany, AddStudentInterestedCompanyResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public AddStudentInterestedCompanyActionHandler(
			final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public AddStudentInterestedCompanyResult execute(
			AddStudentInterestedCompany action, ExecutionContext context)
			throws ActionException {
		AddStudentInterestedCompanyResult result = null;
		try {
			StudentUser student = GetEntityThroughDao.getStudentUser(provider);
			
			if (student != null) {
				List<Company> companys = student.getCompanys();
				if (!isDuplicatedInterestedCompany(companys, action.getCompanyId())) {
					Company company = GetEntityThroughDao.getCompany(action.getCompanyId());
					companys.add(company);
					StudentUserDao studentDao = new StudentUserDao();
					studentDao.updateUser(student);
					result = new AddStudentInterestedCompanyResult("Successfully add company " + company.getName());
				} else {
					result = new AddStudentInterestedCompanyResult("Yon've already add this company!");
				}
				
			} else {
				throw new Exception("No such student!");
			}
			
				
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	private boolean isDuplicatedInterestedCompany(List<Company> companys,
			Long companyId) {
		boolean flag = false;
		for (Company company : companys) {
			if (company.getCompanyId().equals(companyId)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void undo(AddStudentInterestedCompany action,
			AddStudentInterestedCompanyResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AddStudentInterestedCompany> getActionType() {
		return AddStudentInterestedCompany.class;
	}
}

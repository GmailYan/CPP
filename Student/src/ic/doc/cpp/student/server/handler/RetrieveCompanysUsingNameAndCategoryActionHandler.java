package ic.doc.cpp.student.server.handler;

import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.CompanyDao;
import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.shared.action.RetrieveCompanysUsingNameAndCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanysUsingNameAndCategoryResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanysUsingNameAndCategoryActionHandler
		implements
		ActionHandler<RetrieveCompanysUsingNameAndCategory, RetrieveCompanysUsingNameAndCategoryResult> {

	@Inject
	public RetrieveCompanysUsingNameAndCategoryActionHandler() {
	}

	@Override
	public RetrieveCompanysUsingNameAndCategoryResult execute(
			RetrieveCompanysUsingNameAndCategory action, ExecutionContext context)
			throws ActionException {
		RetrieveCompanysUsingNameAndCategoryResult result = null;
		try {
			CompanyDao companyDao = new CompanyDao();
			List<Company> companys = companyDao.retrieveCompanys(
					action.getName(), action.getCategory());
			result = new RetrieveCompanysUsingNameAndCategoryResult(
					CreateDto.createCompanyDtos(companys));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RetrieveCompanysUsingNameAndCategory action,
			RetrieveCompanysUsingNameAndCategoryResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RetrieveCompanysUsingNameAndCategory> getActionType() {
		return RetrieveCompanysUsingNameAndCategory.class;
	}
}

package ic.doc.cpp.student.server.handler;

import java.util.List;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.CompanyCategoryDao;
import ic.doc.cpp.student.server.domain.CompanyCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategoryResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanyCategoryActionHandler implements
		ActionHandler<RetrieveCompanyCategory, RetrieveCompanyCategoryResult> {

	
	@Inject
	public RetrieveCompanyCategoryActionHandler() {
	}
	
	@Override
	public RetrieveCompanyCategoryResult execute(RetrieveCompanyCategory action,
			ExecutionContext context) throws ActionException {
		RetrieveCompanyCategoryResult result = null;
		
		try {
			CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
			List<CompanyCategory> companyCategorys = companyCategoryDao.retrieveCompanyCategorys();
			if (companyCategorys != null) {
				result = new RetrieveCompanyCategoryResult(CreateDto.createCompanyCategoryDtos(companyCategorys));
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveCompanyCategory action,
			RetrieveCompanyCategoryResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveCompanyCategory> getActionType() {
		return RetrieveCompanyCategory.class;
	}
}

package ic.doc.cpp.student.server.handler;

import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.CompanyDao;
import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.shared.action.RetrieveCompanys;
import ic.doc.cpp.student.shared.action.RetrieveCompanysResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanysActionHandler implements
		ActionHandler<RetrieveCompanys, RetrieveCompanysResult> {

	@Inject
	public RetrieveCompanysActionHandler() {
	}

	@Override
	public RetrieveCompanysResult execute(RetrieveCompanys action,
			ExecutionContext context) throws ActionException {
		RetrieveCompanysResult result = null;
		
		try {
			CompanyDao companyDao = new CompanyDao();
			List<Company> companys = companyDao.retrieveCompanys();
			if (companys != null)
				result = new RetrieveCompanysResult(CreateDto.createCompanyDtos(companys));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveCompanys action, RetrieveCompanysResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RetrieveCompanys> getActionType() {
		return RetrieveCompanys.class;
	}
}

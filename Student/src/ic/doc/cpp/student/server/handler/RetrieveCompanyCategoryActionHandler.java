package ic.doc.cpp.student.server.handler;

import java.util.ArrayList;
import java.util.List;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.CompanyCategoryDao;
import ic.doc.cpp.student.server.domain.CompanyCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategoryResult;
import ic.doc.cpp.student.shared.dto.CompanyCategoryDto;
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
		
		CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
		
		try {
			List<CompanyCategory> companyCategorys = companyCategoryDao.retrieveCompanyCategorys();
			
			if (companyCategorys != null) {
				List<CompanyCategoryDto> companyCategoryDtos = new ArrayList<CompanyCategoryDto>(companyCategorys.size());
				
				for (CompanyCategory category : companyCategorys) {
					companyCategoryDtos.add(createCompanyCategoryDto(category));
				}
				result = new RetrieveCompanyCategoryResult(companyCategoryDtos);
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	private CompanyCategoryDto createCompanyCategoryDto(CompanyCategory category) {
		return new CompanyCategoryDto(category.getCategoryId(), 
				category.getCategoryName(), category.getParentId());
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

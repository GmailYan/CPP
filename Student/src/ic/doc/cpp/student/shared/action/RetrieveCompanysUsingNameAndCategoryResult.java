package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.student.shared.dto.CompanyDto;

import java.util.List;

public class RetrieveCompanysUsingNameAndCategoryResult implements Result {

	private List<CompanyDto> companyDtos;

	@SuppressWarnings("unused")
	private RetrieveCompanysUsingNameAndCategoryResult() {
		// For serialization only
	}

	public RetrieveCompanysUsingNameAndCategoryResult(List<CompanyDto> companyDtos) {
		this.companyDtos = companyDtos;
	}

	public List<CompanyDto> getCompanyDtos() {
		return companyDtos;
	}
}

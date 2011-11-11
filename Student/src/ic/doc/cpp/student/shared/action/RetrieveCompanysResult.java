package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.student.shared.dto.CompanyDto;

import java.util.List;

public class RetrieveCompanysResult implements Result {

	private List<CompanyDto> companyDtos;

	@SuppressWarnings("unused")
	private RetrieveCompanysResult() {
		// For serialization only
	}

	public RetrieveCompanysResult(List<CompanyDto> companyDtos) {
		this.companyDtos = companyDtos;
	}

	public List<CompanyDto> getCompanyDtos() {
		return companyDtos;
	}
}

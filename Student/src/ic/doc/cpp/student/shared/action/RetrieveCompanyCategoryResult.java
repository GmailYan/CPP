package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.student.shared.dto.CompanyCategoryDto;

import java.util.List;

public class RetrieveCompanyCategoryResult implements Result {

	private List<CompanyCategoryDto> companyCategoryDtos;

	@SuppressWarnings("unused")
	private RetrieveCompanyCategoryResult() {
		// For serialization only
	}

	public RetrieveCompanyCategoryResult(List<CompanyCategoryDto> companyCategoryDtos) {
		this.companyCategoryDtos = companyCategoryDtos;
	}

	public List<CompanyCategoryDto> getCompanyCategoryDtos() {
		return companyCategoryDtos;
	}
}

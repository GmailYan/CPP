package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.AddStudentInterestedCompanyResult;
import java.lang.Long;

public class AddStudentInterestedCompany extends
		ActionImpl<AddStudentInterestedCompanyResult> {

	private Long companyId;

	@SuppressWarnings("unused")
	private AddStudentInterestedCompany() {
		// For serialization only
	}

	public AddStudentInterestedCompany(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}
}

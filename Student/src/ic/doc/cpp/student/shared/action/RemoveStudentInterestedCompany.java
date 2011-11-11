package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.RemoveStudentInterestedCompanyResult;
import java.lang.Long;

public class RemoveStudentInterestedCompany extends
		ActionImpl<RemoveStudentInterestedCompanyResult> {

	private Long companyId;

	@SuppressWarnings("unused")
	private RemoveStudentInterestedCompany() {
		// For serialization only
	}

	public RemoveStudentInterestedCompany(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}
}

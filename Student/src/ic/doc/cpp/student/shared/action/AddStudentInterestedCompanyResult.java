package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

public class AddStudentInterestedCompanyResult implements Result {

	private String message;
	
	@SuppressWarnings("unused")
	private AddStudentInterestedCompanyResult() {
		// For serialization only
	}
	
	public AddStudentInterestedCompanyResult(String companyName) {
		this.message = companyName;
	}

	public String getMessage() {
		return message;
	}
	
}

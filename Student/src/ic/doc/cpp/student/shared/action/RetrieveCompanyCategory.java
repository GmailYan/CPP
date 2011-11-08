package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;



public class RetrieveCompanyCategory extends 
	ActionImpl<RetrieveCompanyCategoryResult> {

	public RetrieveCompanyCategory() {
	}

	@Override
	public boolean isSecured() {
		return true;
	}
}

package ic.doc.cpp.student.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import ic.doc.cpp.student.server.handler.RetrieveCompanyCategoryActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategory;
import ic.doc.cpp.student.shared.action.Login;
import ic.doc.cpp.student.server.handler.LoginActionHandler;
import ic.doc.cpp.student.server.handler.validator.LoggedInActionValidator;
import ic.doc.cpp.student.shared.action.RetrieveStudentUserInformation;
import ic.doc.cpp.student.server.handler.RetrieveStudentUserInformationActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveEvents;
import ic.doc.cpp.student.server.handler.RetrieveEventsActionHandler;
import ic.doc.cpp.student.shared.action.UpdateStudentUserInformation;
import ic.doc.cpp.student.server.handler.UpdateStudentUserInformationActionHandler;
import ic.doc.cpp.student.shared.action.UpdatePassword;
import ic.doc.cpp.student.server.handler.UpdatePasswordActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedCompanies;
import ic.doc.cpp.student.server.handler.RetrieveStudentInterestedCompaniesActionHandler;
import ic.doc.cpp.student.shared.action.RemoveStudentInterestedCompany;
import ic.doc.cpp.student.server.handler.RemoveStudentInterestedCompanyActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveCompanys;
import ic.doc.cpp.student.server.handler.RetrieveCompanysActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveCompanyEvents;
import ic.doc.cpp.student.server.handler.RetrieveCompanyEventsActionHandler;
import ic.doc.cpp.student.shared.action.AddStudentInterestedCompany;
import ic.doc.cpp.student.server.handler.AddStudentInterestedCompanyActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveCompanysUsingNameAndCategory;
import ic.doc.cpp.student.server.handler.RetrieveCompanysUsingNameAndCategoryActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryId;
import ic.doc.cpp.student.server.handler.RetrieveEventsUsingCategoryIdActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, LoginActionHandler.class);
		
		bindHandler(RetrieveCompanyCategory.class, 
				RetrieveCompanyCategoryActionHandler.class, 
				LoggedInActionValidator.class);

		bindHandler(RetrieveStudentUserInformation.class,
				RetrieveStudentUserInformationActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveEvents.class, RetrieveEventsActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(UpdateStudentUserInformation.class,
				UpdateStudentUserInformationActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(UpdatePassword.class, UpdatePasswordActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveStudentInterestedCompanies.class,
				RetrieveStudentInterestedCompaniesActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RemoveStudentInterestedCompany.class,
				RemoveStudentInterestedCompanyActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveCompanys.class,
				RetrieveCompanysActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveCompanyEvents.class,
				RetrieveCompanyEventsActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(AddStudentInterestedCompany.class,
				AddStudentInterestedCompanyActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveCompanysUsingNameAndCategory.class,
				RetrieveCompanysUsingNameAndCategoryActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveEventsUsingCategoryId.class,
				RetrieveEventsUsingCategoryIdActionHandler.class,
				LoggedInActionValidator.class);
	}
}

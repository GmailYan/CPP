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
	}
}

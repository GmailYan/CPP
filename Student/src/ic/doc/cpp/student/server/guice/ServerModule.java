package ic.doc.cpp.student.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import ic.doc.cpp.student.server.handler.RetrieveCompanyCategoryActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategory;
import ic.doc.cpp.student.shared.action.Login;
import ic.doc.cpp.student.server.handler.LoginActionHandler;
import ic.doc.cpp.student.server.handler.validator.LoggedInActionValidator;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, LoginActionHandler.class);
		bindHandler(RetrieveCompanyCategory.class, RetrieveCompanyCategoryActionHandler.class, LoggedInActionValidator.class);
	}
}

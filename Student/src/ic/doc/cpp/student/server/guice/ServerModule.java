package ic.doc.cpp.student.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import ic.doc.cpp.student.server.handler.RetrieveCompanyCategoryActionHandler;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategory;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(RetrieveCompanyCategory.class,
				RetrieveCompanyCategoryActionHandler.class);
	}
}

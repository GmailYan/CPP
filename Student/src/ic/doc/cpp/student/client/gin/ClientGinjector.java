package ic.doc.cpp.student.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

import ic.doc.cpp.student.client.LoggedInGatekeeper;
import ic.doc.cpp.student.client.gin.ClientModule;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.inject.client.AsyncProvider;
import ic.doc.cpp.student.client.core.StudentPagePresenter;
import ic.doc.cpp.student.client.core.calendar.CalendarPresenter;
import ic.doc.cpp.student.client.core.companydata.CompanyDataPresenter;
import ic.doc.cpp.student.client.core.eventdata.EventDataPresenter;
import ic.doc.cpp.student.client.login.SignInPagePresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AsyncProvider<StudentPagePresenter> getStudentPagePresenter();

	AsyncProvider<CalendarPresenter> getCalendarPresenter();

	AsyncProvider<CompanyDataPresenter> getCompanyDataPresenter();

	AsyncProvider<EventDataPresenter> getEventDataPresenter();

	AsyncProvider<SignInPagePresenter> getSignInPagePresenter();
	
	LoggedInGatekeeper getLoggedInGatekeeper();

}

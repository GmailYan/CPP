package ic.doc.cpp.student.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import ic.doc.cpp.student.client.place.ClientPlaceManager;
import ic.doc.cpp.student.client.core.StudentPagePresenter;
import ic.doc.cpp.student.client.core.StudentPageView;
import ic.doc.cpp.student.client.place.DefaultPlace;
import ic.doc.cpp.student.client.place.NameTokens;
import ic.doc.cpp.student.client.core.calendar.CalendarPresenter;
import ic.doc.cpp.student.client.core.calendar.CalendarView;
import ic.doc.cpp.student.client.core.companydata.CompanyDataPresenter;
import ic.doc.cpp.student.client.core.companydata.CompanyDataView;
import ic.doc.cpp.student.client.core.eventdata.EventDataPresenter;
import ic.doc.cpp.student.client.core.eventdata.EventDataView;
import ic.doc.cpp.student.client.login.SignInPagePresenter;
import ic.doc.cpp.student.client.login.SignInPageView;
import ic.doc.cpp.student.client.login.ErrorDialogPopupPresenter;
import ic.doc.cpp.student.client.login.ErrorDialogPopupView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(StudentPagePresenter.class,
				StudentPagePresenter.MyView.class, StudentPageView.class,
				StudentPagePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(
				NameTokens.signin);

		bindPresenter(CalendarPresenter.class, CalendarPresenter.MyView.class,
				CalendarView.class, CalendarPresenter.MyProxy.class);

		bindPresenter(CompanyDataPresenter.class,
				CompanyDataPresenter.MyView.class, CompanyDataView.class,
				CompanyDataPresenter.MyProxy.class);


		bindPresenter(EventDataPresenter.class,
				EventDataPresenter.MyView.class, EventDataView.class,
				EventDataPresenter.MyProxy.class);

		bindPresenter(SignInPagePresenter.class,
				SignInPagePresenter.MyView.class, SignInPageView.class,
				SignInPagePresenter.MyProxy.class);


		bindPresenterWidget(ErrorDialogPopupPresenter.class,
				ErrorDialogPopupPresenter.MyView.class,
				ErrorDialogPopupView.class);
	}
}

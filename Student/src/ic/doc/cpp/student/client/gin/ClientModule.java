package ic.doc.cpp.student.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

import ic.doc.cpp.student.client.place.ClientPlaceManager;
import ic.doc.cpp.student.client.core.StudentPagePresenter;
import ic.doc.cpp.student.client.core.StudentPageView;
import ic.doc.cpp.student.client.place.DefaultPlace;
import ic.doc.cpp.student.client.place.ErrorPlace;
import ic.doc.cpp.student.client.place.NameTokens;
import ic.doc.cpp.student.client.core.calendar.CalendarPresenter;
import ic.doc.cpp.student.client.core.calendar.CalendarView;
import ic.doc.cpp.student.client.core.companydata.CompanyDataPresenter;
import ic.doc.cpp.student.client.core.companydata.CompanyDataView;
import ic.doc.cpp.student.client.core.eventdata.EventDataPresenter;
import ic.doc.cpp.student.client.core.eventdata.EventDataView;
import ic.doc.cpp.student.client.login.SignInPagePresenter;
import ic.doc.cpp.student.client.login.SignInPageView;
import ic.doc.cpp.student.client.core.companydata.CompanySearchFormWidgetPresenter;
import ic.doc.cpp.student.client.core.companydata.CompanySearchFormWidgetView;
import ic.doc.cpp.student.client.core.companydata.CompanyTileGridWidgetPresenter;
import ic.doc.cpp.student.client.core.companydata.CompanyTileGridWidgetView;
import ic.doc.cpp.student.client.core.companydata.CompanyDetailTabSetWidgetPresenter;
import ic.doc.cpp.student.client.core.companydata.CompanyDetailTabSetWidgetView;
import ic.doc.cpp.student.client.core.eventdata.EventListGridWidgetPresenter;
import ic.doc.cpp.student.client.core.eventdata.EventListGridWidgetView;
import ic.doc.cpp.student.client.core.eventdata.EventSearchFormWidgetPresenter;
import ic.doc.cpp.student.client.core.eventdata.EventSearchFormWidgetView;
import ic.doc.cpp.student.client.core.eventdata.EvetnDetailTabsetWidgetPresenter;
import ic.doc.cpp.student.client.core.eventdata.EvetnDetailTabsetWidgetView;
import ic.doc.cpp.student.client.core.CompanyCategoryWidgetPresenter;
import ic.doc.cpp.student.client.core.CompanyCategoryWidgetView;
import ic.doc.cpp.student.shared.SharedTokens;
import ic.doc.cpp.student.client.core.profile.StudentUserProfilePresenter;
import ic.doc.cpp.student.client.core.profile.StudentUserProfileView;
import ic.doc.cpp.student.client.errorpage.ErrorPagePresenter;
import ic.doc.cpp.student.client.errorpage.ErrorPageView;
import ic.doc.cpp.student.client.core.automatch.AutoMatchPresenter;
import ic.doc.cpp.student.client.core.automatch.AutoMatchView;
import ic.doc.cpp.student.client.core.profile.InterestedCompanyWidgetPresenter;
import ic.doc.cpp.student.client.core.profile.InterestedCompanyWidgetView;
import ic.doc.cpp.student.client.core.newsfeed.MainfestWidgetPresenter;
import ic.doc.cpp.student.client.core.newsfeed.MainfestWidgetView;
import ic.doc.cpp.student.client.core.newsfeed.EventsPanelWidgetPresenter;
import ic.doc.cpp.student.client.core.newsfeed.EventsPanelWidgetView;
import ic.doc.cpp.student.client.core.newsfeed.EventsFeedPresenter;
import ic.doc.cpp.student.client.core.newsfeed.EventsFeedView;
import ic.doc.cpp.student.client.core.profile.FileUploadPopupWidgetPresenter;
import ic.doc.cpp.student.client.core.profile.FileUploadPopupWidgetView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		
		// Protect against XSRF attacks - securityCookieName = "JSESSIONID";
	    bindConstant().annotatedWith(SecurityCookie.class).to(SharedTokens.securityCookieName);
	    
	    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	    bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
	    bind(RootPresenter.class).asEagerSingleton();
	    bind(PlaceManager.class).to(ClientPlaceManager.class).in(Singleton.class);
		
	    // set default page
	    bindConstant().annotatedWith(DefaultPlace.class).to(
	    		NameTokens.signin);
	    
	    // set error page
	    bindConstant().annotatedWith(ErrorPlace.class).to(
	    		NameTokens.errorpage);
	    

	    bindPresenter(StudentPagePresenter.class,
				StudentPagePresenter.MyView.class, StudentPageView.class,
				StudentPagePresenter.MyProxy.class);


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


		bindSingletonPresenterWidget(CompanySearchFormWidgetPresenter.class,
				CompanySearchFormWidgetPresenter.MyView.class,
				CompanySearchFormWidgetView.class);

		bindSingletonPresenterWidget(CompanyTileGridWidgetPresenter.class,
				CompanyTileGridWidgetPresenter.MyView.class,
				CompanyTileGridWidgetView.class);

		bindSingletonPresenterWidget(CompanyDetailTabSetWidgetPresenter.class,
				CompanyDetailTabSetWidgetPresenter.MyView.class,
				CompanyDetailTabSetWidgetView.class);

		bindSingletonPresenterWidget(EventListGridWidgetPresenter.class,
				EventListGridWidgetPresenter.MyView.class,
				EventListGridWidgetView.class);

		bindSingletonPresenterWidget(EventSearchFormWidgetPresenter.class,
				EventSearchFormWidgetPresenter.MyView.class,
				EventSearchFormWidgetView.class);

		bindSingletonPresenterWidget(EvetnDetailTabsetWidgetPresenter.class,
				EvetnDetailTabsetWidgetPresenter.MyView.class,
				EvetnDetailTabsetWidgetView.class);

		bindSingletonPresenterWidget(CompanyCategoryWidgetPresenter.class,
				CompanyCategoryWidgetPresenter.MyView.class,
				CompanyCategoryWidgetView.class);

		bindPresenter(StudentUserProfilePresenter.class,
				StudentUserProfilePresenter.MyView.class,
				StudentUserProfileView.class,
				StudentUserProfilePresenter.MyProxy.class);

		bindPresenter(ErrorPagePresenter.class,
				ErrorPagePresenter.MyView.class, ErrorPageView.class,
				ErrorPagePresenter.MyProxy.class);

		bindPresenter(AutoMatchPresenter.class,
				AutoMatchPresenter.MyView.class, AutoMatchView.class,
				AutoMatchPresenter.MyProxy.class);

		bindSingletonPresenterWidget(InterestedCompanyWidgetPresenter.class,
				InterestedCompanyWidgetPresenter.MyView.class,
				InterestedCompanyWidgetView.class);



		bindSingletonPresenterWidget(MainfestWidgetPresenter.class,
				MainfestWidgetPresenter.MyView.class, MainfestWidgetView.class);

		bindSingletonPresenterWidget(EventsPanelWidgetPresenter.class,
				EventsPanelWidgetPresenter.MyView.class,
				EventsPanelWidgetView.class);


		bindPresenter(EventsFeedPresenter.class,
				EventsFeedPresenter.MyView.class, EventsFeedView.class,
				EventsFeedPresenter.MyProxy.class);

		bindSingletonPresenterWidget(FileUploadPopupWidgetPresenter.class,
				FileUploadPopupWidgetPresenter.MyView.class,
				FileUploadPopupWidgetView.class);
	}
}

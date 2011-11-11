package ic.doc.cpp.student.client.core.profile;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;

import ic.doc.cpp.student.client.LoggedInGatekeeper;
import ic.doc.cpp.student.client.core.StudentPagePresenter;
import ic.doc.cpp.student.client.place.NameTokens;
import ic.doc.cpp.student.shared.FieldVerifier;
import ic.doc.cpp.student.shared.action.RetrieveStudentUserInformation;
import ic.doc.cpp.student.shared.action.RetrieveStudentUserInformationResult;
import ic.doc.cpp.student.shared.action.UpdatePassword;
import ic.doc.cpp.student.shared.action.UpdatePasswordResult;
import ic.doc.cpp.student.shared.action.UpdateStudentUserInformation;
import ic.doc.cpp.student.shared.action.UpdateStudentUserInformationResult;
import ic.doc.cpp.student.shared.dto.StudentUserDto;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class StudentUserProfilePresenter
		extends
		Presenter<StudentUserProfilePresenter.MyView, StudentUserProfilePresenter.MyProxy> {

	private final DispatchAsync dispatcher;
	private final InterestedCompanyWidgetPresenter interestedCompanyWidgetPresenter;
	
	public static final Object TYPE_RevealInterestedCompany = new Object(); 
	
	public interface MyView extends View {

		HandlerRegistration addPasswordSettingUpdateButtonClickHandler(
				ClickHandler handler);

		HandlerRegistration addBasicInformationUpdateButtonClickHandler(
				ClickHandler handler);

		void setData(StudentUserDto studentUserDto);

		boolean validateBasicInformationForm();

		StudentUserDto getBasicFormValueDto();

		boolean validatePasswordSettingForm();

		String getOrinalPassword();

		String getNewPassword();

		HandlerRegistration addTabSelectedHandler(TabSelectedHandler handler);

	}

	@ProxyCodeSplit
	@NameToken(NameTokens.profile)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<StudentUserProfilePresenter> {
	}

	@Inject
	public StudentUserProfilePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher, 
			final InterestedCompanyWidgetPresenter interestedCompanyWidgetPresenter) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.interestedCompanyWidgetPresenter = interestedCompanyWidgetPresenter;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().addBasicInformationUpdateButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (getView().validateBasicInformationForm()) {
					StudentUserDto studentDto = getView().getBasicFormValueDto();
					dispatcher.execute(new UpdateStudentUserInformation(studentDto), new AsyncCallback<UpdateStudentUserInformationResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Fail on updateBasicStudentInformation() - " + caught.getLocalizedMessage());
							SC.say("Error", "Fail on Updating Basic Information");
						}

						@Override
						public void onSuccess(
								UpdateStudentUserInformationResult result) {
							SC.say("Update Basic Information", "Update Successful");
						}
					});
				}
			}
		}));
		
		registerHandler(getView().addPasswordSettingUpdateButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (getView().validatePasswordSettingForm()){
					if (FieldVerifier.isValidPassword(getView().getNewPassword())) {
						dispatcher.execute(new UpdatePassword(getView().getOrinalPassword(), getView().getNewPassword()), 
								new AsyncCallback<UpdatePasswordResult>() {
	
							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Fail on updatePassword() - " + caught.getLocalizedMessage());
								SC.say("Error", "Fail on Updating Passwrod");
							}
	
							@Override
							public void onSuccess(UpdatePasswordResult result) {
								SC.say("Upadate Password", "Updating Passwrod successful");
							}
						});
					} else {
						SC.say("Error", "Invalid new Password format, please read how to set a Password!");
					}
					
				}
			}
			
		}));
		
		registerHandler(getView().addTabSelectedHandler(new TabSelectedHandler() {
			
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				setInSlot(TYPE_RevealInterestedCompany, interestedCompanyWidgetPresenter);
			}
		}));
	}

	@Override
	protected void onReset() {
		super.onReset();
		retrieveStudentInformation();
	}


	@Override
	protected void onReveal() {
		super.onReveal();
	}

	private void retrieveStudentInformation() {
		dispatcher.execute(new RetrieveStudentUserInformation(), new AsyncCallback<RetrieveStudentUserInformationResult>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fail on retrieveStudentInformation() - " + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(RetrieveStudentUserInformationResult result) {
				getView().setData(result.getStudentUserDto());
			}
		});
	}
}

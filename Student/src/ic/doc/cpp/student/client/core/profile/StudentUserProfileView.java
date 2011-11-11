package ic.doc.cpp.student.client.core.profile;

import ic.doc.cpp.student.shared.dto.StudentUserDto;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class StudentUserProfileView extends ViewImpl implements
		StudentUserProfilePresenter.MyView {
	
	private final VLayout widget;
	private final TabSet mainTabSet; 

	private VLayout interestedCompanySlot;
	
	private DynamicForm basicInformationForm;
	private DynamicForm passwordSettingForm;
	
	private ButtonItem basicInformationUpdateButton;
	private ButtonItem passwordSettingUpdateButton;
	
	private Tab interestedCompanyTab;
	
	@Inject
	public StudentUserProfileView() {
		// initialise main view container
		widget = new VLayout();
		
		// initialise tabset
		mainTabSet = new TabSet(); 
		
		// Basic information tab
		Tab basciInformationTab = initBasicInformationTab();
		
		// interested company tab
		interestedCompanyTab = new Tab();
		interestedCompanyTab.setTitle("Interested Companies");
		interestedCompanySlot = new VLayout();
		interestedCompanyTab.setPane(interestedCompanySlot);
		
		// password setting tab
		Tab passwordSettingTab = initPasswordSettingTab();
		
		// add tabs into tabset
		mainTabSet.setTabs(basciInformationTab, interestedCompanyTab, passwordSettingTab);
		
		// add tabset into main container
		widget.setMembers(mainTabSet);
	}



	public Tab initPasswordSettingTab() {
		Tab passwordSettingTab = new Tab("Password Setting");
		
		passwordSettingForm = new DynamicForm();
		
		PasswordItem originalPassword = new PasswordItem("originalPassword", "Original Password");
		originalPassword.setRequired(true);
		originalPassword.setLength(64);
		LengthRangeValidator lengthValidator = new LengthRangeValidator();
		lengthValidator.setMin(8);
		originalPassword.setValidators(lengthValidator);
		
		PasswordItem newPasswordItem = new PasswordItem("newPassword", "New Password");
		newPasswordItem.setRequired(true);
		newPasswordItem.setLength(64);
		
		PasswordItem newPasswordItem2 = new PasswordItem("newPassword2", "New Password Again");
		newPasswordItem2.setTitle("Password Again");
		newPasswordItem2.setRequired(true);
		newPasswordItem2.setLength(64);
		MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
		matchesValidator.setOtherField("newPassword");
		matchesValidator.setErrorMessage("Passwords do not match");
		newPasswordItem2.setValidators(matchesValidator);
		
		passwordSettingUpdateButton = new ButtonItem("passwordUpdateButton", "Update");
		
		passwordSettingForm.setFields(originalPassword, newPasswordItem, newPasswordItem2, passwordSettingUpdateButton);
		
		passwordSettingTab.setPane(passwordSettingForm);
		return passwordSettingTab;
	}



	public Tab initBasicInformationTab() {
		Tab basciInformationTab = new Tab("User Information");
		
		basicInformationForm = new DynamicForm();
		
		TextItem firstNameItem = new TextItem("firstName", "First Name");
		firstNameItem.setLength(100);
		firstNameItem.setRequired(true);
		
		TextItem lastNameItem = new TextItem("lastName", "Last Name");
		lastNameItem.setLength(100);
		lastNameItem.setRequired(true);
		
		TextItem genderItem = new TextItem("gender", "Gender");
		genderItem.setLength(20);
		genderItem.setRequired(true);
		
		TextItem emailItem = new TextItem("email", "Email");
		emailItem.setRequired(true);
		RegExpValidator emailValidator = new RegExpValidator();
		emailValidator.setErrorMessage("Invalid email address");
		emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
		emailItem.setValidators(emailValidator);
		
		basicInformationUpdateButton = new ButtonItem("basicUpdateButton", "Update");
		
		basicInformationForm.setFields(firstNameItem, lastNameItem, genderItem, emailItem, basicInformationUpdateButton);
		
		basciInformationTab.setPane(basicInformationForm);
		return basciInformationTab;
	}
	
	
	
	@Override
	public HandlerRegistration addPasswordSettingUpdateButtonClickHandler(
			ClickHandler handler) {
		return passwordSettingUpdateButton.addClickHandler(handler);
	}
	
	@Override
	public HandlerRegistration addBasicInformationUpdateButtonClickHandler(
			ClickHandler handler) {
		return basicInformationUpdateButton.addClickHandler(handler);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setData(StudentUserDto studentUserDto) {
		basicInformationForm.setValue("firstName", studentUserDto.getFirstName());
		basicInformationForm.setValue("lastName", studentUserDto.getLastName());
		basicInformationForm.setValue("gender", studentUserDto.getGender());
		basicInformationForm.setValue("email", studentUserDto.getEmail());
	}



	@Override
	public boolean validateBasicInformationForm() {
		return basicInformationForm.validate();
	}



	@Override
	public StudentUserDto getBasicFormValueDto() {
		StudentUserDto studentDto = new StudentUserDto();
		studentDto.setEmail(basicInformationForm.getValue("email").toString());
		studentDto.setFirstName(basicInformationForm.getValue("firstName").toString());
		studentDto.setLastName(basicInformationForm.getValue("lastName").toString());
		studentDto.setGender(basicInformationForm.getValue("gender").toString());
		return studentDto;
	}



	@Override
	public boolean validatePasswordSettingForm() {
		return passwordSettingForm.validate();
	}



	@Override
	public String getOrinalPassword() {
		return passwordSettingForm.getValue("originalPassword").toString();
	}



	@Override
	public String getNewPassword() {
		return passwordSettingForm.getValue("newPassword").toString();
	}
	
	@Override
	public HandlerRegistration addTabSelectedHandler(TabSelectedHandler handler) {
		return interestedCompanyTab.addTabSelectedHandler(handler);
	}


	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == StudentUserProfilePresenter.TYPE_RevealInterestedCompany) {
			if (content != null && content instanceof VLayout) {
				GWT.log("Reveal iterested companys");
				interestedCompanySlot.setMembers((VLayout)content);
			}
		}
	}
	
	
}

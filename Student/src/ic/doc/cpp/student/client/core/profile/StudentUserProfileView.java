package ic.doc.cpp.student.client.core.profile;

import ic.doc.cpp.student.shared.dto.StudentUserDto;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
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
	
	private IButton basicInformationUpdateButton;
	private IButton passwordSettingUpdateButton;
	
	private LinkItem cvUploadLinkItem;
	
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

		VLayout panel = new VLayout();
		VLayout header = initHeader("Password Setting", "You can change your password here.");
		header.setStyleName("crm-Wizard-Header");
		VLayout southLayout = new VLayout();
		
		VLayout body = initPasswordSettingTabBody();
		
		passwordSettingUpdateButton = new IButton("Update");
		VLayout footer = initFooter(passwordSettingUpdateButton);
		
		southLayout.addMember(body);
		southLayout.addMember(footer);

		panel.addMember(header);
		panel.addMember(southLayout);
			
		passwordSettingTab.setPane(panel);
		return passwordSettingTab;
	}
	
	
	public VLayout initPasswordSettingTabBody() {
		VLayout body = new VLayout();
		
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
		passwordSettingForm.setFields(originalPassword, newPasswordItem, newPasswordItem2);
		
		body.setStyleName("crm-Wizard-Body");
		body.addMember(passwordSettingForm);
		
		return body;
	}


	public Tab initBasicInformationTab() {
		Tab basciInformationTab = new Tab("User Information");
		
		VLayout panel = new VLayout();
		VLayout header = initHeader("Basic Information", "You can update your information here.");
		header.setStyleName("crm-Wizard-Header");
		VLayout southLayout = new VLayout();
		VLayout body = initBasicInformationTabBody();
		basicInformationUpdateButton = new IButton("Update");
		
		VLayout footer = initFooter(basicInformationUpdateButton);

		southLayout.addMember(body);
		southLayout.addMember(footer);
		
		panel.addMember(header);
	    panel.addMember(southLayout);
		
		basciInformationTab.setPane(panel);
		return basciInformationTab;
	}
	
	
	
	private VLayout initBasicInformationTabBody() {
		basicInformationForm = new DynamicForm();
		basicInformationForm.setCellPadding(2);
		basicInformationForm.setWrapItemTitles(false);
		
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
		
		cvUploadLinkItem = new LinkItem("uploadCV");
		cvUploadLinkItem.setTitle("Upload CV");
		cvUploadLinkItem.setLinkTitle("Click here");
		
		basicInformationForm.setFields(firstNameItem, lastNameItem, genderItem, emailItem, cvUploadLinkItem);
		
		VLayout body = new VLayout();
		body.setStyleName("crm-Wizard-Body");
		body.addMember(basicInformationForm);
		
		return body;
	}



	public static VLayout initHeader(String title, String description) {
	    // initialise the Header layout container
	    VLayout header = new VLayout();
	    header.setWidth100();
	    header.setHeight(58);

	    HLayout line1 = new HLayout();
	    line1.setWidth100();
	    line1.setHeight100();

	    HLayout line2 = new HLayout();
	    line2.setWidth100();
	    line2.setHeight100();

	    // initialise the Name label
	    Label name = new Label();
	    name.setStyleName("crm-Wizard-Name");
	    name.setContents(title);
	    name.setWidth100();

	    // initialise the Description label
	    Label descriptionLabel = new Label();
	    descriptionLabel.setStyleName("crm-Wizard-Description");
	    descriptionLabel.setContents(description);
	    descriptionLabel.setWidth100();

	    // add the labels to the nested layout containers
	    line1.addMember(name);
	    line2.addMember(descriptionLabel);

	    // add the North and South layout containers to the main layout container
	    header.addMember(line1);
	    header.addMember(line2);
	    return header;
	}

	private VLayout initFooter(IButton updateButton) {
		int footerHeight = 48;
		    // initialise the Footer layout container
		VLayout footer = new VLayout();
		footer.setStyleName("crm-Wizard-Footer");
		footer.setWidth100();
		footer.setHeight(footerHeight);
		
		HLayout hLayout = new HLayout();
		hLayout.setWidth100();
		hLayout.setHeight(footerHeight);
		
		updateButton.setShowRollOver(true);
		updateButton.setShowDisabled(true);
		updateButton.setShowDown(true);
		
		
		// layout the OK and Cancel buttons
		hLayout.setLayoutMargin(8);
		hLayout.addMember(new LayoutSpacer());
		hLayout.addMember(updateButton);
		LayoutSpacer padding = new LayoutSpacer();
		padding.setWidth(8);
		hLayout.addMember(padding);
		
		// add the nested layout container to the main layout container
		footer.addMember(hLayout);

		return footer;
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
	
	@Override
	public HandlerRegistration addUploadCVLinkItemClickHandler(
			com.smartgwt.client.widgets.form.fields.events.ClickHandler handler) {
		return cvUploadLinkItem.addClickHandler(handler);
	}
	
	
}

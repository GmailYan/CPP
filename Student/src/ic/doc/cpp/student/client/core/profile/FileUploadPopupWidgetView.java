package ic.doc.cpp.student.client.core.profile;

import com.gwtplatform.mvp.client.PopupViewImpl;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.NamedFrame;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class FileUploadPopupWidgetView extends PopupViewImpl implements
		FileUploadPopupWidgetPresenter.MyView {

	private final PopupPanel widget;
	
	private VLayout panel;
	private VLayout northLayout;
	private VLayout southLayout;

	private DynamicForm uploadForm;
  	private UploadItem uploadItem;
  	
  	private Button okButton;
  	private Button cancelButton;
	
	@Inject
	public FileUploadPopupWidgetView(final EventBus eventBus) {
		super(eventBus);
		widget = new PopupPanel();
		widget.setModal(true);
		widget.add(CVUploadWidgetView());

	}
	
	private VLayout CVUploadWidgetView() {
	    // initialise the main layout container
	    panel = new VLayout();
	    panel.setWidth(400);
	    panel.setHeight(300);

	    // initialise the North layout container
	    northLayout = new VLayout();
	    northLayout.setWidth100();
	    northLayout.setHeight(HEADER_HEIGHT);

	    // initialise the South layout container
	    southLayout = new VLayout();
	    southLayout.setWidth100();
	    southLayout.setHeight100();

	    // add the nested layout containers to the main layout containers
	    VLayout header = StudentUserProfileView.initHeader(NAME,DESCRIPTION);
	    header.setStyleName("popup-header");
	    northLayout.addMember(header);
	    southLayout.addMember(initBody());
	    southLayout.addMember(initFooter());

	    // add the North and South layout containers to the main layout container
	    panel.addMember(northLayout);
	    panel.addMember(southLayout);
	    
	    return panel;
	}

	 private static final int HEADER_HEIGHT = 58;
	 private static final String NAME = "Select a CV to Upload";
	 private static final String DESCRIPTION = "Registered your CV with CPP";
	 
	 private static final String DEFAULT_FILE_UPLOAD_SERVICE_PATH = "upload";
	 private static final String TARGET = "uploadTarget";

	  private VLayout initBody() {

	    // initialise the Footer layout container
	    VLayout body = new VLayout();
	    body.setStyleName("popup-body");

	    // initialise the form
	    uploadForm = new DynamicForm();
	    uploadForm.setWidth100();
	    uploadForm.setMargin(8);
	    uploadForm.setNumCols(2);
	    uploadForm.setCellPadding(2);
	    // uploadForm.setAutoFocus(false);
	    uploadForm.setWrapItemTitles(false);
	    // no ":" after the field name
	    uploadForm.setTitleSuffix(" ");
	    uploadForm.setRequiredTitleSuffix(" ");

	    // initialise the hidden frame
	    NamedFrame frame = new NamedFrame(TARGET);
	    frame.setWidth("1px");
	    frame.setHeight("1px");
	    frame.setVisible(false);

	    uploadForm.setEncoding(Encoding.MULTIPART);
	    uploadForm.setMethod(FormMethod.POST);
	    // set the (hidden) form target
	    uploadForm.setTarget(TARGET);

	    uploadForm.setAction(DEFAULT_FILE_UPLOAD_SERVICE_PATH);

	    // initialise the File name field
	    uploadItem = new UploadItem("filename");
	    uploadItem.setName("filename");
	    uploadItem.setTitle("File name");
	    uploadItem.setWidth(280);

	    // set the fields into the form
	    uploadForm.setFields(uploadItem);

	    // add the Upload Form and the (hidden) Frame to the main layout container
	    body.addMember(uploadForm);
	    body.addMember(frame);

	    return body;
	  }

	  private static final int FOOTER_HEIGHT = 48;

	  private VLayout initFooter() {

	    // initialise the Footer layout container
	    VLayout footer = new VLayout();
	    footer.setStyleName("popup-footer");
	    footer.setWidth100();
	    footer.setHeight(FOOTER_HEIGHT);

	    HLayout hLayout = new HLayout();
	    hLayout.setWidth100();
	    hLayout.setHeight(FOOTER_HEIGHT);

	    // initialise the OK button
	    okButton = new Button("OK");
	    okButton.setWidth("80px");
	    
	    // initialise the Cancel button
	    cancelButton = new Button("Close");
	    cancelButton.setWidth("80px");

	    // layout the OK and Cancel buttons
	    hLayout.setLayoutMargin(8);
	    hLayout.addMember(new LayoutSpacer());
	    hLayout.addMember(okButton);
	    LayoutSpacer padding = new LayoutSpacer();
	    padding.setWidth(8);
	    hLayout.addMember(padding);
	    hLayout.addMember(cancelButton);

	    // add the nested layout container to the main layout container
	    footer.addMember(hLayout);

	    return footer;
	  }
	  
	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public HandlerRegistration addOKButtonClickHandler(ClickHandler handler) {
		return okButton.addClickHandler(handler);
	}

	@Override
	public HandlerRegistration addCancelButtonClickHandler(ClickHandler handler) {
		return cancelButton.addClickHandler(handler);
	}
	
	@Override
	public void submmitForm() {
		uploadForm.submitForm();
	}
	
	@Override
	public void hide() {
		widget.hide();
	}

	@Override
	public Object getDisplayValue() {
		return uploadItem.getDisplayValue();
	}
}

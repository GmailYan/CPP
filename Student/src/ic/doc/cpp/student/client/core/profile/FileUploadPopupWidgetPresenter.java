package ic.doc.cpp.student.client.core.profile;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.PopupView;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.util.SC;

public class FileUploadPopupWidgetPresenter extends
		PresenterWidget<FileUploadPopupWidgetPresenter.MyView> {

	public interface MyView extends PopupView {

		void submmitForm();

		HandlerRegistration addOKButtonClickHandler(ClickHandler handler);

		HandlerRegistration addCancelButtonClickHandler(ClickHandler handler);

		Object getDisplayValue();
	}

	@Inject
	public FileUploadPopupWidgetPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().addOKButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (getView().getDisplayValue() != null) {
					getView().submmitForm();
				} else {
					SC.say("Please select a file.");
				}
			}
		});
		
		getView().addCancelButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				getView().hide();
			}
		});
	}
}

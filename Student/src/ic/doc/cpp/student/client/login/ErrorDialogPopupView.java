package ic.doc.cpp.student.client.login;

import com.gwtplatform.mvp.client.PopupViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class ErrorDialogPopupView extends PopupViewImpl implements
		ErrorDialogPopupPresenter.MyView {

	private final PopupPanel widget;

	public interface ErrorDialogPopupViewUiBinder extends UiBinder<Widget, ErrorDialogPopupView> {
	}
	
	private static ErrorDialogPopupViewUiBinder uiBinder = GWT.create(ErrorDialogPopupViewUiBinder.class);
	
	@UiField
	Button okButton;

	@Inject
	public ErrorDialogPopupView(final EventBus eventBus) {
		super(eventBus);
		widget = (PopupPanel) uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public Button getOkButton() {
		return okButton;
	}
}

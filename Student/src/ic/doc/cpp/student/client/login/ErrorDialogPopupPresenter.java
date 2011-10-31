package ic.doc.cpp.student.client.login;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.PopupView;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;

public class ErrorDialogPopupPresenter extends
		PresenterWidget<ErrorDialogPopupPresenter.MyView> {

	public interface MyView extends PopupView {
		Button getOkButton();
	}

	@Inject
	public ErrorDialogPopupPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				((PopupPanel) getView().asWidget()).hide();
			}
			
		});
	}
	
	
}

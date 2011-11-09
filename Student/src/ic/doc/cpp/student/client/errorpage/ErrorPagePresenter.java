package ic.doc.cpp.student.client.errorpage;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.student.client.place.NameTokens;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class ErrorPagePresenter extends
		Presenter<ErrorPagePresenter.MyView, ErrorPagePresenter.MyProxy> {

	private final PlaceManager placeManager;
	
	public interface MyView extends View {

		HandlerRegistration addOkButtonClickHandler(ClickHandler handler);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.errorpage)
	@NoGatekeeper
	public interface MyProxy extends ProxyPlace<ErrorPagePresenter> {
	}

	@Inject
	public ErrorPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}

	@Override
	protected void revealInParent() {
		RevealRootLayoutContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().addOkButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest placeRequest = new PlaceRequest(NameTokens.signin);
			    placeManager.revealPlace(placeRequest);
			}
			
		}));
	}
}

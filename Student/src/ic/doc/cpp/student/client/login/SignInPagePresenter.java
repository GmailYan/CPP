package ic.doc.cpp.student.client.login;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.student.client.place.NameTokens;
import ic.doc.cpp.student.shared.FieldVerifier;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;

public class SignInPagePresenter extends
		Presenter<SignInPagePresenter.MyView, SignInPagePresenter.MyProxy> {
	
	private final PlaceManager placeManager;
	private final ErrorDialogPopupPresenter errorDialog;
	
	public interface MyView extends View {

		String getUserName();

		String getPassword();

		Button getSignInButton();

		void resetAndFocus();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.signin)
	public interface MyProxy extends ProxyPlace<SignInPagePresenter> {
	}

	@Inject
	public SignInPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager, final ErrorDialogPopupPresenter errorDialog) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.errorDialog = errorDialog;
	}

	  @Override
	  protected void onBind() {
	    super.onBind();
	    registerHandler(getView().getSignInButton().addClickHandler(
	        new ClickHandler() {
	          @Override
	          public void onClick(ClickEvent event) {
	            sendCredentialsToServer();
	          }
	        }));
	  }

	  @Override
	  protected void onReset() {
	    super.onReset();
	    getView().resetAndFocus();
	  }

	  @Override
	  protected void revealInParent() {
	    RevealRootLayoutContentEvent.fire(this, this);
	 }

	  private void sendCredentialsToServer() {
	    String userName = getView().getUserName();
	    String password = getView().getPassword();
	    
	    if (FieldVerifier.isValidUserName(userName) && (FieldVerifier.isValidPassword(password))) {
	      PlaceRequest myRequest = new PlaceRequest(NameTokens.studentpage);
	      
	      // If needed, add URL parameters in this way:
	      // myRequest = myRequest.with( "key1", "param1" ).with( "key2", "param2" );
	      placeManager.revealPlace(myRequest); 
	    }
	    else {
	      showErrorDialog();
	    }
	  }

	  public void showErrorDialog() {
	    addToPopupSlot(errorDialog);
	  }
}

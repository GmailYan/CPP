package ic.doc.cpp.student.client.login;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

import ic.doc.cpp.student.client.CurrentUser;
import ic.doc.cpp.student.client.place.NameTokens;
import ic.doc.cpp.student.shared.FieldVerifier;
import ic.doc.cpp.student.shared.action.Login;
import ic.doc.cpp.student.shared.action.LoginResult;
import ic.doc.cpp.student.shared.exception.LoginException;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.smartgwt.client.util.SC;

public class SignInPagePresenter extends
		Presenter<SignInPagePresenter.MyView, SignInPagePresenter.MyProxy> {
	
	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	
	public interface MyView extends View {

		String getUserName();

		String getPassword();

		Button getSignInButton();

		void resetAndFocus();
	}

	@ProxyStandard
	@NameToken(NameTokens.signin)
	@NoGatekeeper
	public interface MyProxy extends ProxyPlace<SignInPagePresenter> {
	}

	@Inject
	public SignInPagePresenter(final EventBus eventBus, final MyView view, final DispatchAsync dispatcher,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
	}

	  @Override
	  protected void onBind() {
	    super.onBind();
	    registerHandler(getView().getSignInButton().addClickHandler(
	        new ClickHandler() {
	          @Override
	          public void onClick(ClickEvent event) {
	        	  if (FieldVerifier.isValidUserName(getView().getUserName()) 
	        			  && (FieldVerifier.isValidPassword(getView().getPassword()))) {
	        		  sendCredentialsToServer();
	        	  } else {
	        		  SC.say("Sign in", "You must enter a valid User name and Password.");
	        		  getView().resetAndFocus();
	        	  }
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
		  String login = getView().getUserName();
		  String password = getView().getPassword();
		  
		  dispatcher.execute(new Login(login, password),
				  new AsyncCallback<LoginResult>() {

					@Override
					public void onFailure(Throwable caught) {
						String message = "onFailure() - " + caught.getLocalizedMessage();
					        
						if (caught instanceof LoginException) {
							message = "onFailure() - " + "Invalid User name or Password." ;
						}
			          
						getView().resetAndFocus();
						SC.say(message);
					}

					@Override
					public void onSuccess(LoginResult result) {
						CurrentUser currentUser = new CurrentUser(getView().getUserName());
					        
				        LoginAuthenticatedEvent.fire(eventBus, currentUser);
					        
				        PlaceRequest placeRequest = new PlaceRequest(NameTokens.studentpage);
				        placeManager.revealPlace(placeRequest);       
					}
			  
		  });
	    
	  }

}

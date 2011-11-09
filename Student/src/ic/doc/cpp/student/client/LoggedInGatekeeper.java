package ic.doc.cpp.student.client;

import ic.doc.cpp.student.client.login.LoginAuthenticatedEvent;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class LoggedInGatekeeper implements Gatekeeper {
	private final EventBus eventBus;
	private CurrentUser currentUser = null;
	
	@Inject
	public LoggedInGatekeeper(final EventBus eventBus) {
		this.eventBus = eventBus;
		
		this.eventBus.addHandler(LoginAuthenticatedEvent.getType(), new LoginAuthenticatedEvent.LoginAuthenticatedHandler() {

			@Override
			public void onLoginAuthenticated(LoginAuthenticatedEvent event) {
				currentUser = event.getCurrentUser();
			}
			
		});
	}
	
	@Override
	public boolean canReveal() {
		boolean loggedIn = false;
		if (currentUser != null) {
			loggedIn = currentUser.isLoggedIn();
		}
		return loggedIn;
	}

}

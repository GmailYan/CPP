package ic.doc.cpp.student.client.login;

import ic.doc.cpp.student.client.CurrentUser;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;

public class LoginAuthenticatedEvent extends
		GwtEvent<LoginAuthenticatedEvent.LoginAuthenticatedHandler> {
	private final CurrentUser user;
	
	public static Type<LoginAuthenticatedHandler> TYPE = new Type<LoginAuthenticatedHandler>();

	public interface LoginAuthenticatedHandler extends EventHandler {
		void onLoginAuthenticated(LoginAuthenticatedEvent event);
	}

	public LoginAuthenticatedEvent(CurrentUser user) {
		this.user = user;
		
	}

	public CurrentUser getCurrentUser() {
		return user;
	}
	
	@Override
	protected void dispatch(LoginAuthenticatedHandler handler) {
		handler.onLoginAuthenticated(this);
	}

	@Override
	public Type<LoginAuthenticatedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<LoginAuthenticatedHandler> getType() {
		return TYPE;
	}

	public static void fire(EventBus eventBus, CurrentUser currentUser) {
		eventBus.fireEvent(new LoginAuthenticatedEvent(currentUser));
	}
}

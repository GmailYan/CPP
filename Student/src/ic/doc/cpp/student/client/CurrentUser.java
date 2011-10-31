package ic.doc.cpp.student.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;

public class CurrentUser implements HasHandlers {
	
	private boolean isLoggedIn = true;
	
	private final EventBus eventBus;
	
	@Inject
	public CurrentUser(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	
	@Override
	public void fireEvent(GwtEvent<?> event) {
		eventBus.fireEvent(event);
	}


	public boolean isLoggedIn() {
		return isLoggedIn;
	}


	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}

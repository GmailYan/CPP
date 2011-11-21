package ic.doc.cpp.student.client;

import com.google.gwt.core.client.EntryPoint;
import ic.doc.cpp.student.client.gin.ClientGinjector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class Student implements EntryPoint {
	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

	interface GlobalResources extends ClientBundle {
		@NotStrict
		@Source("Student.css")
		CssResource css();
	}
	
	@Override
	public void onModuleLoad() {
		GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
		
		DelayedBindRegistry.bind(ginjector);
		
		ginjector.getPlaceManager().revealCurrentPlace();
		
		RootPanel.get("loading") .setVisible(false);
	}
	
}

package ic.doc.cpp.student.client.core.newsfeed;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.layout.VLayout;

public class EventsFeedView extends ViewImpl implements
		EventsFeedPresenter.MyView {

	private final VLayout container;

	@Inject
	public EventsFeedView() {
		container = new VLayout();
	}

	@Override
	public Widget asWidget() {
		return container;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		GWT.log("StudentPageView.setInSlot()...", null);
		
		if (slot == EventsFeedPresenter.TYPE_RevealEventFeedWidget) {
			if (content != null) {
				container.setMembers((VLayout)content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}
}

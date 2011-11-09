package ic.doc.cpp.student.client.core.automatch;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.VLayout;

public class AutoMatchView extends ViewImpl implements
		AutoMatchPresenter.MyView {

	private final VLayout widget;
	
	@Inject
	public AutoMatchView() {
		widget = new VLayout();
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}

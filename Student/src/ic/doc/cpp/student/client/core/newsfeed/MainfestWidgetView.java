package ic.doc.cpp.student.client.core.newsfeed;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class MainfestWidgetView extends ViewImpl implements
		MainfestWidgetPresenter.MyView {

	private final BasePanel panel; 
	private final ListGrid contents;
	
	@Inject
	public MainfestWidgetView() {
		contents = new ListGrid();
		contents.setID("mainfestWidgetView");
		panel = new BasePanel("New Events", false, contents);
	}
	
	@Override
	public void addPanels(PanelLabel[] panels) {
		panel.addPanels(panels);
	}

	@Override
	public HandlerRegistration addTitleClickHandler(ClickHandler handler) {
		return panel.addTitleClickHandler(handler);
	}
	
	@Override
	public HandlerRegistration setEditLabel(String label, String title, ClickHandler handler) {
		return panel.setEditLabel(label, title, handler);
	}
	
	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public HandlerRegistration addRecordClickHandler(RecordClickHandler handler) {
		return contents.addRecordClickHandler(handler);
	}
}

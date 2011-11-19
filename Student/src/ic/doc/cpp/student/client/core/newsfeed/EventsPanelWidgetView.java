package ic.doc.cpp.student.client.core.newsfeed;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class EventsPanelWidgetView extends ViewImpl implements
		EventsPanelWidgetPresenter.MyView {

	private final BasePanel panel;
	private final DetailListGrid contents;
	private String originalTitle = "";
	
	@Inject
	public EventsPanelWidgetView() {
		contents = new DetailListGrid();
		contents.setID("EventsPanelWidgetView");
		panel = new BasePanel(true, contents);
	}

	@Override
	public void addPanels(PanelLabel[] panelLabels) {
		panel.addPanels(panelLabels);
	}
	
	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public String getTitle() {
		return panel.getTitle();
	}
	
	@Override
	public void setTitle(String title) {
		panel.setTitle(title);
		originalTitle = title;
	}
	
	@Override
	public void setTitle(int unReadNumber) {
		if (unReadNumber == 0) {
			setTitle(originalTitle);
		} else {
			String newTitle = originalTitle + "("+unReadNumber+")";
			panel.setTitle(newTitle);
		}
	}
	
	
	@Override
	public HandlerRegistration addBackLabelClickHandler(ClickHandler handler) {
		return panel.addBackHandler(handler);
	}
	
	
	@Override
	public HandlerRegistration addRecordClickHandler(RecordClickHandler handler) {
		return contents.addRecordClickHandler(handler);
	}

	@Override
	public void collapseAll() {
		contents.collapseAll();
		contents.setLastExpandRecord(null);
	}

}

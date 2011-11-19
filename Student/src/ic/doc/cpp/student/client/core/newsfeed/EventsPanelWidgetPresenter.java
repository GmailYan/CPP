package ic.doc.cpp.student.client.core.newsfeed;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class EventsPanelWidgetPresenter extends
		PresenterWidget<EventsPanelWidgetPresenter.MyView> {
	
	public interface MyView extends View {
		void setTitle(String title);
		HandlerRegistration addBackLabelClickHandler(ClickHandler handler);
		void addPanels(PanelLabel[] panelLabels);
		String getTitle();
		void setTitle(int unReadNumber);
		HandlerRegistration addRecordClickHandler(RecordClickHandler handler);
		void collapseAll();
	}
	
	private Feed curr = null;
	
	@Inject
	public EventsPanelWidgetPresenter(final EventBus eventBus, final MyView view,
			final DispatchAsync dispatcher, final MainfestWidgetPresenter parent) {
		super(eventBus, view);

		getView().addRecordClickHandler(new RecordClickHandler() {
			
			@Override
			public void onRecordClick(RecordClickEvent event) {
				if (event.getRecord() instanceof PanelLabel) {
					PanelLabel record = (PanelLabel)event.getRecord();
					Command cmd = record.getCmd();
					if (cmd != null) {
						cmd.execute();
						if (curr != null)
							getView().setTitle(curr.getUnReadEvents());
					}
				}
			}
		});
		
		getView().addBackLabelClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				getView().collapseAll();
				RevealNewsFeedPageEvent.fire(eventBus, parent, null);
			}
		});
	}

	public void show(Feed feed) {
		curr = feed;
		getView().setTitle(feed.getTitle());
		getView().addPanels(feed.getEventLabels().toArray(new PanelLabel[0]));
		getView().setTitle(feed.getUnReadEvents());
	}

}

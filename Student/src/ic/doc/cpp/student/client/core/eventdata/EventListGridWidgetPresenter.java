package ic.doc.cpp.student.client.core.eventdata;

import ic.doc.cpp.student.client.core.data.EventDetailXmlDS;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class EventListGridWidgetPresenter extends
		PresenterWidget<EventListGridWidgetPresenter.MyView> {

	public interface MyView extends View {
		void setDataSource(DataSource ds);
		HandlerRegistration addRollOverButtonClickHandler(ClickHandler handler);
		Record getRollOverRecord();
		HandlerRegistration addRecordClickHandler(
				RecordClickHandler recordClickHandler);
		void filterData(Criteria findValues);
		Record getSelectedRecord();
	}

	@Inject
	public EventListGridWidgetPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setDataSource(EventDetailXmlDS.getInstance());
		registerHandler(getView().addRollOverButtonClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				SC.say("You like " + getView().getRollOverRecord().getAttribute("title"));
			}
		}));
	}
}

package ic.doc.cpp.student.client.core.eventdata;

import ic.doc.cpp.student.client.core.data.EventDetailXmlDS;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class EventSearchFormWidgetPresenter extends
		PresenterWidget<EventSearchFormWidgetPresenter.MyView> {

	public interface MyView extends View {
		void setDataSource(DataSource ds);
		CheckboxItem getFindInCategory();
		HandlerRegistration addFindListener(ClickHandler handler);
		Criteria getValuesAsCriteria();
		String getValueAsString(String field);
	}

	@Inject
	public EventSearchFormWidgetPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setDataSource(EventDetailXmlDS.getInstance());
	}
}

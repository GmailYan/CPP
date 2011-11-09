package ic.doc.cpp.student.client.core.eventdata;

import ic.doc.cpp.student.client.core.data.EventDetailXmlDS;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;

public class EvetnDetailTabsetWidgetPresenter extends
		PresenterWidget<EvetnDetailTabsetWidgetPresenter.MyView> {

	private final EventListGridWidgetPresenter eventListGrid;
	
	public interface MyView extends View {

		void setDataSource(DataSource ds);

		void setData(Record[] records);
	}

	@Inject
	public EvetnDetailTabsetWidgetPresenter(final EventBus eventBus,
			final MyView view, final EventListGridWidgetPresenter eventListGrid) {
		super(eventBus, view);
		this.eventListGrid = eventListGrid;
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setDataSource(EventDetailXmlDS.getInstance());
	}
	
	public void updateDetails() {
		Record selectedRecord  = eventListGrid.getView().getSelectedRecord();  
		getView().setData(new Record[]{selectedRecord});  
	}

	public void clearDetails() {
		getView().setData((Record[]) null);  
	}
	
}

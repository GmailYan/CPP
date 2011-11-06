package ic.doc.cpp.student.client.core.companydata;

import ic.doc.cpp.student.client.core.data.CompanyDetailXmlDS;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;

public class CompanyTileGridWidgetPresenter extends
		PresenterWidget<CompanyTileGridWidgetPresenter.MyView> {

	public interface MyView extends View {

		void setDataSource(DataSource ds);

		HandlerRegistration addRecordClickHandler(
				RecordClickHandler recordClickHandler);

		HandlerRegistration addShowContextMenuHandler(
				ShowContextMenuHandler showContextMenuHandler);

		Record getSelectedRecord();
		void filterData(Criteria findValues);
	}

	@Inject
	public CompanyTileGridWidgetPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setDataSource(CompanyDetailXmlDS.getInstance());
	}

}

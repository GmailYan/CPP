package ic.doc.cpp.student.client.core.companydata;

import ic.doc.cpp.student.client.core.data.CompanyDetailDataSource;
import ic.doc.cpp.student.client.util.CreateRecordFromDto;
import ic.doc.cpp.student.shared.action.RetrieveCompanys;
import ic.doc.cpp.student.shared.action.RetrieveCompanysResult;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;

public class CompanyTileGridWidgetPresenter extends
		PresenterWidget<CompanyTileGridWidgetPresenter.MyView> {

	final DispatchAsync dispatcher;
	
	public interface MyView extends View {

		void setDataSource(DataSource ds);

		HandlerRegistration addRecordClickHandler(
				RecordClickHandler recordClickHandler);

		HandlerRegistration addShowContextMenuHandler(
				ShowContextMenuHandler showContextMenuHandler);

		Record getSelectedRecord();

		void setData(Record[] records);
	
	}

	@Inject
	public CompanyTileGridWidgetPresenter(final EventBus eventBus, final MyView view,
			final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setDataSource(CompanyDetailDataSource.getInstance());
	}

	@Override
	protected void onReset() {
		super.onReset();
		retrieveCompanyDetails();
	}
	
	private void retrieveCompanyDetails() {
		dispatcher.execute(new RetrieveCompanys(), new AsyncCallback<RetrieveCompanysResult>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fail on retrieveCompanyDetails() - " + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(RetrieveCompanysResult result) {
				if (result != null) {
					getView().setData(
							CreateRecordFromDto.createCompanyTileRecordsFromCompanyDtos(
									result.getCompanyDtos()).toArray(new Record[0]));
				}
			}

		});
	}
	
	public Long getSelectedCompanyId() {
		return getView().getSelectedRecord().getAttributeAsLong("companyId");
	}
}

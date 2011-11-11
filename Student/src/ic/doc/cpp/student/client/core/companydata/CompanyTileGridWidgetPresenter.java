package ic.doc.cpp.student.client.core.companydata;

import java.util.ArrayList;
import java.util.List;

import ic.doc.cpp.student.client.core.data.CompanyDetailDataSource;
import ic.doc.cpp.student.client.util.CreateRecordFromDto;
import ic.doc.cpp.student.shared.action.RetrieveCompanys;
import ic.doc.cpp.student.shared.action.RetrieveCompanysResult;
import ic.doc.cpp.student.shared.dto.CompanyDto;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;

public class CompanyTileGridWidgetPresenter extends
		PresenterWidget<CompanyTileGridWidgetPresenter.MyView> {

	final DispatchAsync dispatcher;
	private List<Record> oldRecords = new ArrayList<Record>(); 
	
	public interface MyView extends View {

		void setDataSource(DataSource ds);

		HandlerRegistration addRecordClickHandler(
				RecordClickHandler recordClickHandler);

		HandlerRegistration addShowContextMenuHandler(
				ShowContextMenuHandler showContextMenuHandler);

		Record getSelectedRecord();
		void filterData(Criteria findValues);

		void addData(Record[] record);

		void addData(Record record);

		void removeData(Record record);
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
				if (result != null)
					updateDataSource(result.getCompanyDtos());
			}

		});
	}

	private void updateDataSource(List<CompanyDto> companyDtos) {
		List<Record> latestRecords = new ArrayList<Record>();
		for (CompanyDto companyDto : companyDtos) {
			Record record = CreateRecordFromDto.createCompanyTileRecordFromCompanyDto(companyDto);
			latestRecords.add(record);
		}
		if (oldRecords.size() == 0) {
			for (Record record : latestRecords) {
				getView().addData(record);
				oldRecords.add(record);
			}
		} else {
			List<Record> addRecords = new ArrayList<Record>();
			List<Record> delRecords = new ArrayList<Record>();
			for (Record o : oldRecords) {
				boolean flag = false;
				for (Record n : latestRecords) {
					if (o.equals(n)) {
						flag = true;
						break;
					}
				}
				if (!flag)
					delRecords.add(o);
			}
			
			for (Record n : latestRecords) {
				boolean flag = true;
				for (Record o : oldRecords) {
					if (o.equals(n)) {
						flag = false;
						break;
					}
				}
				if (flag)
					addRecords.add(n);
			}
			
			for (Record record : addRecords) {
				getView().addData(record);
				oldRecords.add(record);
			}
			
			for (Record record : delRecords) {
				getView().removeData(record);
				oldRecords.remove(record);
			}
		}
		
		
	}

	public Long getSelectedCompanyId() {
		return getView().getSelectedRecord().getAttributeAsLong("companyId");
	}
}

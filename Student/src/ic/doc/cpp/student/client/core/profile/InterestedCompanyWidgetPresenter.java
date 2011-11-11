package ic.doc.cpp.student.client.core.profile;

import ic.doc.cpp.student.client.util.CreateRecordFromDto;
import ic.doc.cpp.student.shared.action.RemoveStudentInterestedCompany;
import ic.doc.cpp.student.shared.action.RemoveStudentInterestedCompanyResult;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedCompanies;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedCompaniesResult;
import ic.doc.cpp.student.shared.dto.CompanyDto;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class InterestedCompanyWidgetPresenter extends
		PresenterWidget<InterestedCompanyWidgetPresenter.MyView> {

	private final DispatchAsync dispatcher;
	
	private List<Record> oldRecords = new ArrayList<Record>(); 
	
	public interface MyView extends View {

		void setData(List<CompanyDto> companys);

		HandlerRegistration addRemoveMenuItemClickHandler(ClickHandler handler);

		Record getSelectedRecord();

		void deleteRecord(Record record);

		HandlerRegistration addShowContextMenuHandler(ShowContextMenuHandler showContextMenuHandler);

		Menu getInterestedCompanyListMenu();

		HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler);

		Canvas getDetailViewer();

		void setDeatilViewerData(Record record);

		HandlerRegistration addWinModalCloseClickHandler(
				CloseClickHandler closeClickHandler);

		void hideWinModal();

		void showWinModal();

		void addRecord(Record record);
		
	}

	@Inject
	public InterestedCompanyWidgetPresenter(final EventBus eventBus,
			final MyView view, final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().addWinModalCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				getView().hideWinModal();
			}
		}));
		
		registerHandler(getView().addDoubleClickHandler(new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				Record record = getView().getSelectedRecord();
				getView().setDeatilViewerData(record);
				getView().showWinModal();
			}
		}));
		
		registerHandler(getView().addShowContextMenuHandler(new ShowContextMenuHandler() {
			
			@Override
			public void onShowContextMenu(ShowContextMenuEvent event) {
				getView().getInterestedCompanyListMenu().showContextMenu();
				event.cancel();
			}
		}));
		
		registerHandler(getView().addRemoveMenuItemClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				final Record record = getView().getSelectedRecord();
				if (record != null) {
					Long companyId = record.getAttributeAsLong("companyId");
					dispatcher.execute(new RemoveStudentInterestedCompany(companyId), 
							new AsyncCallback<RemoveStudentInterestedCompanyResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Fail on removeStudentInteresteCompany() RPC call - " + caught.getLocalizedMessage());
						}

						@Override
						public void onSuccess(
								RemoveStudentInterestedCompanyResult result) {
							SC.say("Remove Interested List", "Remove " + record.getAttribute("companyName") + " Successful!");
							getView().deleteRecord(record);
						}
					});
				}
			}
		}));
	}

	@Override
	protected void onReset() {
		super.onReset();
		retrieveInterestedCompanyData();
	}

	private void retrieveInterestedCompanyData() {
		dispatcher.execute(new RetrieveStudentInterestedCompanies(), 
				new AsyncCallback<RetrieveStudentInterestedCompaniesResult>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("fail on retrieveInterestedCompanyData()...");
			}

			@Override
			public void onSuccess(RetrieveStudentInterestedCompaniesResult result) {
				if (result != null) {
					List<CompanyDto> companyDto = result.getCompanyDto();
					updateDataSource(companyDto);
				}
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
				getView().addRecord(record);
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
				getView().addRecord(record);
				oldRecords.add(record);
			}
			
			for (Record record : delRecords) {
				getView().deleteRecord(record);
				oldRecords.remove(record);
			}
		}
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}
}

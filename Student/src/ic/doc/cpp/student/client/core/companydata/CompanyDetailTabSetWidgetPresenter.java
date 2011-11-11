package ic.doc.cpp.student.client.core.companydata;

import java.util.List;

import ic.doc.cpp.student.client.core.data.CompanyDetailDataSource;
import ic.doc.cpp.student.client.core.data.CompanyEventsDetailDataSource;
import ic.doc.cpp.student.client.core.data.CompanyTileRecord;
import ic.doc.cpp.student.client.util.CreateRecordFromDto;
import ic.doc.cpp.student.shared.dto.EventDto;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class CompanyDetailTabSetWidgetPresenter extends
		PresenterWidget<CompanyDetailTabSetWidgetPresenter.MyView> {

	private final CompanyTileGridWidgetPresenter companyTileGrid;
	
	public interface MyView extends View {
		void setDataSource(DataSource companyDetailDS, DataSource eventDetailDS);
		HandlerRegistration addTabSeletedHandler(TabSelectedHandler handler);
		DetailViewer getCompanyViewer();
		Label getEventListGridLabel();
		int getSelectedTabNumber();
		ListGrid getEventListGrid();
		void setCompanyViewerData(Record[] records);
		void updateTab(int i, Canvas pane);
		void eventListGridFetchData(Criteria criteria);
		void setEventListGridData(Record[] records);
	}


	@Inject
	public CompanyDetailTabSetWidgetPresenter(final EventBus eventBus,
			final MyView view, final CompanyTileGridWidgetPresenter companyTileGrid) {
		super(eventBus, view);
		this.companyTileGrid = companyTileGrid;
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().setDataSource(CompanyDetailDataSource.getInstance(),
				CompanyEventsDetailDataSource.getInstance());
		
		registerHandler(getView().addTabSeletedHandler(new TabSelectedHandler() {  
            public void onTabSelected(TabSelectedEvent event) {  
                updateDetails();  
            }  
        }));
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}
	
	public void clearDetails() {  
		int selectedTab = getView().getSelectedTabNumber();  
		if (selectedTab == 0) {  
		    //view tab : show empty message  
		    getView().setCompanyViewerData((Record[]) null);
		} else {  
		    // edit tab : show new record editor, or empty message
			getView().updateTab(1, getView().getEventListGridLabel());  
	    }  
    }  
	  
	public void updateDetails() {  
        CompanyTileRecord selectedRecord  = (CompanyTileRecord)companyTileGrid.getView().getSelectedRecord();  
        int selectedTab = getView().getSelectedTabNumber();  
        if (selectedTab == 0) {  
        	getView().setCompanyViewerData(new Record[]{selectedRecord});  
        } else {
        	getView().updateTab(1, getView().getEventListGrid());
	    	if (selectedRecord != null) {
//	    		Criteria criteria = new Criteria();
//	    		criteria.addCriteria("companyId", selectedRecord.getAttribute("companyId"));
//	    		getView().eventListGridFetchData(criteria);
	    		List<EventDto> eventDtos = selectedRecord.getEvents();
	    		GWT.log(eventDtos.toString());
	    		String companyName = selectedRecord.getAttribute("name");
	    		getView().setEventListGridData(CreateRecordFromDto.createRecordsFromEventDtos(companyName, eventDtos));
	    	}
        }  
	}


}

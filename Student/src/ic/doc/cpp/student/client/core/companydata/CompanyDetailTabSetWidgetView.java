package ic.doc.cpp.student.client.core.companydata;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class CompanyDetailTabSetWidgetView extends ViewImpl implements
		CompanyDetailTabSetWidgetPresenter.MyView {

	private TabSet widget;
	private DetailViewer companyViewer;
	private ListGrid eventListGrid;
	private Label eventListGridLabel;
	
	
	@Inject
	public CompanyDetailTabSetWidgetView() {
		
		// initialise detail viewer
		companyViewer = new DetailViewer();  
        companyViewer.setWidth100();  
        companyViewer.setMargin(25);  
        companyViewer.setEmptyMessage("Select an item to view its details");  
        
		eventListGridLabel = new Label();  
        eventListGridLabel.setWidth100();  
        eventListGridLabel.setHeight100();  
        eventListGridLabel.setAlign(Alignment.CENTER);  
        eventListGridLabel.setContents("Select a record to edit, or a category to insert a new record into");
		
        eventListGrid = new ListGrid();
        eventListGrid.setShowRollOverCanvas(true);
        eventListGrid.setCanEdit(false);
        eventListGrid.setCanDragRecordsOut(true);

		Tab viewTab = new Tab("View");  
        //viewTab.setIcon("silk/application_form.png");  
        viewTab.setWidth(70);  
        viewTab.setPane(companyViewer);  
  
        Tab eventTab = new Tab("Event");  
        //editTab.setIcon("demoApp/icon_edit.png");  
        eventTab.setWidth(70);  
        eventTab.setPane(eventListGrid);
        
        widget = new TabSet();
        widget.setTabs(viewTab, eventTab);
        
	}
	
	@Override
	public HandlerRegistration addTabSeletedHandler(TabSelectedHandler handler) {
		return widget.addTabSelectedHandler(handler);
	}
	
	@Override
	public void setDataSource(DataSource companyDetailDS, DataSource eventDetailDS) {
		companyViewer.setDataSource(companyDetailDS); 
        eventListGrid.setDataSource(eventDetailDS);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public DetailViewer getCompanyViewer() {
		return companyViewer;
	}

	@Override
	public Label getEventListGridLabel() {
		return eventListGridLabel;
	}

	@Override
	public void updateTab(int i,  Canvas pane) {
		widget.updateTab(i, pane);
		
	}

	@Override
	public int getSelectedTabNumber() {
		return widget.getSelectedTabNumber();
	}

	@Override
	public ListGrid getEventListGrid() {
		return eventListGrid;
	}

	@Override
	public void setCompanyViewerData(Record[] records) {
		companyViewer.setData(records);
	}

	@Override
	public void eventListGridFetchData(Criteria criteria) {
		eventListGrid.fetchData(criteria);
	}
}

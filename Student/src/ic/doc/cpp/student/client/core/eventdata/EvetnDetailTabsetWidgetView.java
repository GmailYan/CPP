package ic.doc.cpp.student.client.core.eventdata;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class EvetnDetailTabsetWidgetView extends ViewImpl implements
		EvetnDetailTabsetWidgetPresenter.MyView {
	
	private TabSet widget;
	private DetailViewer eventViewer;

	@Inject
	public EvetnDetailTabsetWidgetView() {
		GWT.log("init EvetnDetailTabsetWidgetView()...");
		
		eventViewer = new DetailViewer();
	    eventViewer.setWidth100();  
	    eventViewer.setMargin(25); 
		eventViewer.setWidth100();  
		eventViewer.setMargin(25);  
		eventViewer.setEmptyMessage("Select an item to view its details");
		
		Tab viewTab = new Tab("View");  
        //viewTab.setIcon("silk/application_form.png");  
        viewTab.setWidth(70);  
        viewTab.setPane(eventViewer);
        
        widget = new TabSet();
        widget.setTabs(viewTab); 
	}
	
	@Override
	public void setDataSource(DataSource ds) {
		eventViewer.setDataSource(ds);  
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setData(Record[] records) {
		eventViewer.setData(records);
	}
}

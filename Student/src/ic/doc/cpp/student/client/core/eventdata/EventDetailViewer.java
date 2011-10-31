package ic.doc.cpp.student.client.core.eventdata;

import ic.doc.cpp.student.client.core.data.EventDetailXmlDS;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class EventDetailViewer extends TabSet {
	
	private DetailViewer itemViewer;
	private ListGrid itemList;

	public EventDetailViewer(ListGrid itemList) {
		GWT.log("init EventDetailViewer()...");
		
		this.itemList = itemList;
		itemViewer = new DetailViewer();
	    itemViewer.setWidth100();  
	    itemViewer.setMargin(25); 
		itemViewer.setDataSource(EventDetailXmlDS.getInstance());  
		itemViewer.setWidth100();  
		itemViewer.setMargin(25);  
		itemViewer.setEmptyMessage("Select an item to view its details");
		Tab viewTab = new Tab("View");  
        //viewTab.setIcon("silk/application_form.png");  
        viewTab.setWidth(70);  
        viewTab.setPane(itemViewer);
        setTabs(viewTab); 
   	}

	public void updateDetails() {
		Record selectedRecord  = itemList.getSelectedRecord();  
		itemViewer.setData(new Record[]{selectedRecord});  
	}

	public void clearDetails() {
		itemViewer.setData((Record[]) null);  
	}

}

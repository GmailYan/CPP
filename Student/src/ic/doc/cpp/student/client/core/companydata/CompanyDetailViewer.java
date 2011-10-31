package ic.doc.cpp.student.client.core.companydata;

import ic.doc.cpp.student.client.core.data.CompanyDetailXmlDS;
import ic.doc.cpp.student.client.core.data.EventDetailXmlDS;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class CompanyDetailViewer extends TabSet{
	private DetailViewer companyViewer;
	private ListGrid eventListGrid;
	private TileGrid companyTileGrid;
	private Label eventListGridLabel;
	
	public CompanyDetailViewer(TileGrid companyTileGrid) {
		this.companyTileGrid = companyTileGrid;
		
		companyViewer = new DetailViewer();  
        
        companyViewer.setWidth100();  
        companyViewer.setMargin(25);  
        companyViewer.setEmptyMessage("Select an item to view its details");  
        companyViewer.setDataSource(CompanyDetailXmlDS.getInstance()); 
        
		eventListGridLabel = new Label();  
        eventListGridLabel.setWidth100();  
        eventListGridLabel.setHeight100();  
        eventListGridLabel.setAlign(Alignment.CENTER);  
        eventListGridLabel.setContents("Select a record to edit, or a category to insert a new record into");
		
        eventListGrid = new ListGrid();
        eventListGrid.setShowRollOverCanvas(true);
        eventListGrid.setCanEdit(false);
        eventListGrid.setCanDragRecordsOut(true);
        eventListGrid.setDataSource(EventDetailXmlDS.getInstance());
        
		Tab viewTab = new Tab("View");  
        //viewTab.setIcon("silk/application_form.png");  
        viewTab.setWidth(70);  
        viewTab.setPane(companyViewer);  
  
        Tab eventTab = new Tab("Event");  
        //editTab.setIcon("demoApp/icon_edit.png");  
        eventTab.setWidth(70);  
        eventTab.setPane(eventListGrid);
        
        setTabs(viewTab, eventTab);
        
        addTabSelectedHandler(new TabSelectedHandler() {  
            public void onTabSelected(TabSelectedEvent event) {  
                updateDetails();  
            }  
        });  
	}
	
	public void clearDetails(Record selectedCategoryRecord) {  
		int selectedTab = getSelectedTabNumber();  
		if (selectedTab == 0) {  
		    //view tab : show empty message  
		    companyViewer.setData((Record[]) null);  
		} else {  
		    // edit tab : show new record editor, or empty message
			 updateTab(1, eventListGridLabel);  
	    }  
    }  
	  
	public void updateDetails() {  
        Record selectedRecord  = companyTileGrid.getSelectedRecord();  
        int selectedTab = getSelectedTabNumber();  
        if (selectedTab == 0) {  
        	companyViewer.setData(new Record[]{selectedRecord});  
        } else {
        	updateTab(1, eventListGrid);
	    	if (selectedRecord != null) {
	    		Criteria criteria = new Criteria();
	    		criteria.addCriteria("CID", selectedRecord.getAttribute("CID"));
	    		eventListGrid.fetchData(criteria); 
	    	}
        }  
	}  
	
}
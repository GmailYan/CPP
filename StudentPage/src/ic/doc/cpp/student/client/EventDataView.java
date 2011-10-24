package ic.doc.cpp.student.client;

import ic.doc.cpp.student.client.data.EventCategoryXmlDS;
import ic.doc.cpp.student.client.data.EventDetailXmlDS;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class EventDataView extends VLayout {
	private static final String DESCRIPTION = "EventDataView";
	
	private SectionStack dataview;
	private ItemListGrid eventList;
	private Menu eventListMenu;  
	private EventDetailViewer eventDetailViewer;  
	private EventSearchForm searchForm;
	private DataSource eventDetailDS, eventCategoryDS;
	private EventCategoryTreeGrid categoryTree;
	
	public EventDataView() {
		super();
		GWT.log("init EventDataView()...", null);
		
		// initialise dataview
		dataview = new SectionStack();
		dataview.setVisibilityMode(VisibilityMode.MULTIPLE);
      	dataview.setAnimateSections(true);
		
      	// initialise datasource				
      	eventDetailDS = EventDetailXmlDS.getInstance();
		eventCategoryDS = EventCategoryXmlDS.getInstance();
		
		// set up CompanyCategoryGridTree
		categoryTree = EventCategoryTreeGrid.getInstance();
		categoryTree.addNodeClickHandler(new NodeClickHandler() {  
            public void onNodeClick(NodeClickEvent event) {
            	CheckboxItem findInCategory = searchForm.getFindInCategory();
            	findInCategory.setValue(true);
                findItems();
            }  
        });
		
		// set up Search form
      	searchForm = new EventSearchForm(eventDetailDS);
      	searchForm.setHeight(60);  
      	searchForm.addFindListener(new ClickHandler() {
      		public void onClick(ClickEvent event) {  
      			findItems();  
      		}  
      	});
      	
      	// set up context menu
      	setupContextMenu();
      	
      	// set up Company TileList
      	eventList = new ItemListGrid(eventDetailDS);
      	eventList.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {
				eventDetailViewer.updateDetails();
			}
      		
      	});
      	eventList.addShowContextMenuHandler(new ShowContextMenuHandler() {
			
			@Override
			public void onShowContextMenu(ShowContextMenuEvent event) {
				eventListMenu.showContextMenu();
				event.cancel();
			}
		});
      	
        // Set up dataview section stack
        SectionStackSection findSection = new SectionStackSection("Find Event");  
        findSection.setItems(searchForm);  
        findSection.setExpanded(true);  
  
        SectionStackSection eventListSection = new SectionStackSection("Event List");  
        eventListSection.setItems(eventList);
        
        eventListSection.setExpanded(true);  
  
        eventDetailViewer = new EventDetailViewer(eventDetailDS, eventCategoryDS, eventList);  
        SectionStackSection eventDetailsSection = new SectionStackSection("Event Details");  
        eventDetailsSection.setItems(eventDetailViewer);  
        eventDetailsSection.setExpanded(true);
	    
        dataview.setSections(findSection, eventListSection, eventDetailsSection);  
        
	    // add the List Grid to the CompanyData View layout container
      	addMember(dataview);
	}
	
	private void setupContextMenu() {  
        eventListMenu = new Menu();  
        eventListMenu.setCellHeight(22);  
  
        final MenuItem editMenuItem = new MenuItem("Add to Like List");  
        editMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
            public void onClick(MenuItemClickEvent event) {  
            	SC.say("You like " + eventList.getSelectedRecord().getAttribute("name"));
            }  
        });
  
        eventListMenu.setData(editMenuItem);
	}
	
	
	
	public void findItems() {
        Criteria findValues = null;  
        String categoryName = "";
        String useCategoryTreeValue = searchForm.getValueAsString("findInCategory");
        ListGridRecord selectedCategory = categoryTree.getSelectedRecord();

        if (useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	categoryName = selectedCategory.getAttribute("categoryName");  
            findValues = searchForm.getValuesAsCriteria();
            findValues.addCriteria("category", categoryName);  
        } else if (!useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	findValues = searchForm.getValuesAsCriteria();  
            findValues.addCriteria("category", categoryName); 
        } else {
        	SC.say("Please select a category to use category!");
        }
        
        if (findValues != null)
        	eventList.filterData(findValues);
        
    	eventDetailViewer.clearDetails();
        
    }  
	
	public static class Factory implements ContextAreaFactory {
		
		private String id;
		public static Canvas view;
		
		public Canvas create() {
			if (view == null)
				view = new EventDataView();
			id = view.getID();
			
			GWT.log("EventDataView.Factory.create()->view.getID() - " + id, null);
			return view;
		}
		
		public String getID() {
			return id;
		}
		
		public String getDescription() {
			return DESCRIPTION;
		}
	}
}

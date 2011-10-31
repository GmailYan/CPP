package ic.doc.cpp.student.client.core.eventdata;

import ic.doc.cpp.student.client.core.EventCategoryTreeGrid;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class EventDataView extends ViewImpl implements
		EventDataPresenter.MyView {

	private VLayout widget;
	private SectionStack dataview;
	private ListGrid eventList;
	private EventDetailViewer eventDetailViewer;  
	private EventSearchForm searchForm;
	private EventCategoryTreeGrid categoryTree;
	
	@Inject
	public EventDataView() {
		GWT.log("init EventDataView()...", null);
		
		// initialise main container
		widget = new VLayout();
		
		// initialise dataview
		dataview = new SectionStack();
		dataview.setVisibilityMode(VisibilityMode.MULTIPLE);
      	dataview.setAnimateSections(true);
      	
      	// initialise event Category GridTree
 		categoryTree = EventCategoryTreeGrid.getInstance();
 		
 		// initialise Search form
      	searchForm = new EventSearchForm();
      	searchForm.setHeight(60);
      	
      	// initialise event TileList
      	eventList = new EventListGrid();
      	
      	// initialise event detail viewer
      	eventDetailViewer = new EventDetailViewer(eventList);  
      	
        // Set up dataview section stack
        SectionStackSection findSection = new SectionStackSection("Find Event");  
        findSection.setItems(searchForm);  
        findSection.setExpanded(true);  
  
        SectionStackSection eventListSection = new SectionStackSection("Event List");  
        eventListSection.setItems(eventList);
        
        eventListSection.setExpanded(true);  
  
        SectionStackSection eventDetailsSection = new SectionStackSection("Event Details");  
        eventDetailsSection.setItems(eventDetailViewer);  
        eventDetailsSection.setExpanded(true);
	    
        dataview.setSections(findSection, eventListSection, eventDetailsSection);  
        
	    // add the List Grid to the CompanyData View layout container
      	widget.addMember(dataview);
	}
	

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public SectionStack getDataview() {
		return dataview;
	}

	@Override
	public ListGrid getEventList() {
		return eventList;
	}

	@Override
	public EventDetailViewer getEventDetailViewer() {
		return eventDetailViewer;
	}

	@Override
	public EventSearchForm getSearchForm() {
		return searchForm;
	}

	@Override
	public EventCategoryTreeGrid getCategoryTree() {
		return categoryTree;
	}
}

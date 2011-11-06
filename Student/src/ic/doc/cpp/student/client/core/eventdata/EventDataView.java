package ic.doc.cpp.student.client.core.eventdata;

import ic.doc.cpp.student.client.core.EventCategoryTreeGrid;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class EventDataView extends ViewImpl implements
		EventDataPresenter.MyView {

	private VLayout widget;
	private SectionStack dataview;
	private EventCategoryTreeGrid categoryTree;
	private VLayout searchFormSlot, eventListGridSlot, eventDetailTabsetSlot;
	
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
 		
        // Set up dataview section stack
        SectionStackSection findSection = new SectionStackSection("Find Event");
        searchFormSlot = new VLayout();
        searchFormSlot.setHeight(20);
        findSection.setItems(searchFormSlot);  
        findSection.setExpanded(true);  
  
        SectionStackSection eventListSection = new SectionStackSection("Event List");  
        eventListGridSlot = new VLayout();
        eventListSection.addItem(eventListGridSlot);
        eventListSection.setExpanded(true);  
  
        SectionStackSection eventDetailsSection = new SectionStackSection("Event Details");
        eventDetailTabsetSlot = new VLayout();
        eventDetailsSection.setItems(eventDetailTabsetSlot);  
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
	public EventCategoryTreeGrid getCategoryTree() {
		return categoryTree;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		GWT.log("CompanyDataView.setInSlot()...", null);
		
		if (slot == EventDataPresenter.TYPE_RevealEventSearchForm) {
			if (content != null) {
				searchFormSlot.addMember(content);
			}
		} else if (slot == EventDataPresenter.TYPE_RevealEventListGrid) {
			if (content != null) {
				eventListGridSlot.addMember(content);
			}
		} else if (slot == EventDataPresenter.TYPE_RevealEventDetailTabSet) {
			if (content != null) {
				eventDetailTabsetSlot.addMember(content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}
}

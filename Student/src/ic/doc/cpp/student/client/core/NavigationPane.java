package ic.doc.cpp.student.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

public class NavigationPane extends VLayout {
	 private static final int WEST_WIDTH = 200;
	  
	 private SectionStack navigationSectionStack ;
		
	 public NavigationPane() {
		 super();
		 GWT.log("init NavigationPane()...", null);
		
		 // initialise the Navigation Pane layout container
		 setStyleName("crm-NavigationPane");	
		 setWidth(WEST_WIDTH);
		 // this.setShowResizeBar(true); 
		
		 // initialise the Section Stack
		 navigationSectionStack = new SectionStack();
		 navigationSectionStack.setWidth(WEST_WIDTH);
		 navigationSectionStack.setVisibilityMode(VisibilityMode.MUTEX);
		 navigationSectionStack.setShowResizeBar(true);
		 navigationSectionStack.setShowExpandControls(true);
		 navigationSectionStack.setAnimateSections(true);	
	    
		 // add the Section Stack to the Navigation Pane layout container
		 this.addMember(navigationSectionStack); 
	 }
	  
	 public void addListgridSection(String sectionName, ListgridSectionStackSectionRecord[] sectionData) {
		 navigationSectionStack.addSection(new ListgridSectionStackSection(sectionName, sectionData));
	 } 

	 public void addCategorySection(CategorySectionStackSection section) {
		 navigationSectionStack.addSection(section);
	 }

	 public void expandSection(int section) {
		 navigationSectionStack.expandSection(section);
	 }

	public HandlerRegistration addSectionHeaderClickHandler(SectionHeaderClickHandler clickHandler) {
		    return navigationSectionStack.addSectionHeaderClickHandler(clickHandler);
	}    
		  
	public HandlerRegistration addRecordClickHandler(String sectionName, RecordClickHandler clickHandler) {
   
		GWT.log("addRecordClickHandler(sectionName, clickHandler) - " + sectionName);   
      
		SectionStackSection[] sections = navigationSectionStack.getSections();

		for (int i = 0; i < sections.length; i++) {
			SectionStackSection sectionStackSection = sections[i];
      
			if (sectionName.contentEquals(sections[i].getTitle())) {
				return ((ListgridSectionStackSection)sectionStackSection).addRecordClickHandler(clickHandler);
			}
		}
		return null;    
	}  
}

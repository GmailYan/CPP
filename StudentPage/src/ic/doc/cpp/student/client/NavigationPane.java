package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

public class NavigationPane extends VLayout {
	 private static final int WEST_WIDTH = 200;
	  
	  private SectionStack navigationSectionStack ;
		
	  public NavigationPane(SectionHeaderClickHandler handler) {
		super();
		GWT.log("init NavigationPane()...", null);
		
	    // initialise the Navigation Pane layout container
		this.addStyleName("crm-NavigationPane");	
	    this.setWidth(WEST_WIDTH);
	    // this.setShowResizeBar(true); 
		
		// initialise the Section Stack
	    navigationSectionStack = new SectionStack();
	    navigationSectionStack.setWidth(WEST_WIDTH);
	    navigationSectionStack.setVisibilityMode(VisibilityMode.MUTEX);
	    navigationSectionStack.setShowResizeBar(true);
	    navigationSectionStack.setShowExpandControls(true);
	    navigationSectionStack.setAnimateSections(true);	
	    navigationSectionStack.addSectionHeaderClickHandler(handler);	
	    
	    // add the Section Stack to the Navigation Pane layout container
		this.addMember(navigationSectionStack); 
	  }	
	  
	  public void addListgridSection(String sectionName, ListgridSectionStackSectionRecord[] sectionData, 
			  		  RecordClickHandler clickHandler) {
		navigationSectionStack.addSection(new ListgridSectionStackSection(sectionName, sectionData,
								clickHandler));
	  } 

	  public void expandSection(int section) {
		navigationSectionStack.expandSection(section);
	  }

	public void addCategorySection(CategorySectionStackSection section) {
		navigationSectionStack.addSection(section);
	}
}

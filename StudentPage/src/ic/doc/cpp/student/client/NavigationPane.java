package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;

public class NavigationPane extends SectionStack {
	 private static final int WEST_WIDTH = 200;
	  
	  private SectionStack sectionStack ;
		
	  public NavigationPane() {
		super();
					
		GWT.log("init NavigationPane()...", null);
		
	    // initialise the Navigation Pane layout container
		this.addStyleName("crm-NavigationPane");	
	    this.setWidth(WEST_WIDTH);
	    // this.setShowResizeBar(true); 
		
		// initialise the Section Stack
	    sectionStack = new SectionStack();
	    sectionStack.setWidth(WEST_WIDTH);
	    sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
	    sectionStack.setShowExpandControls(true);
	    sectionStack.setAnimateSections(true);	
	    
	    // add the Section Stack to the Navigation Pane layout container
		this.addMember(sectionStack); 
	  }	
	  
	  public void add(String sectionName, NavigationPaneRecord[] sectionData, 
			  		  RecordClickHandler clickHandler) {
		sectionStack.addSection(new NavigationPaneSection(sectionName, sectionData,
								clickHandler));
	  } 

	  public void expandSection(int section) {
		sectionStack.expandSection(section);
	  } 
}

package ic.doc.cpp.student.client.core;

import ic.doc.cpp.student.client.place.NameTokens;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class StudentPageView extends ViewImpl implements
		StudentPagePresenter.MyView {
	private static final int NORTH_HEIGHT = 85; // MASTHEAD_HEIGHT + APPLICATION_MENU_HEIGHT
	private static final int DEFAULT_MENU_WIDTH = 70;
	private static final String DEFAULT_MARGIN = "0px";  
	
	private VLayout mainLayout;	
	private HLayout northLayout;  
	private HLayout southLayout;
	private VLayout westLayout;
	private VLayout eastLayout;

	private final ApplicationMenu applicationMenu ;
	private final Masthead masthead;
	private final NavigationPane navigationPane;
	
	private VLayout companyCategoryTreeGridSlot;
	private final EventCategoryTreeGrid eventCategoryTreeGrid;
	
	
	@Inject
	public StudentPageView() {
		super();
		
		applicationMenu = new ApplicationMenu();
		masthead = new Masthead();
		navigationPane = new NavigationPane();
		
		eventCategoryTreeGrid = EventCategoryTreeGrid.getInstance();
		
		// get rid of scroll bars and window's margin
		Window.enableScrolling(true);
		Window.setMargin(DEFAULT_MARGIN);
		
		// initialise the main layout container
		mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		
		// initialise the North layout container
		northLayout = new HLayout();
		northLayout.setHeight(NORTH_HEIGHT);
		
		// initialise application menu
		initApplicationMenu();
		
		// initialise the nested layout conatainer
		VLayout vLayout = new VLayout();
		vLayout.addMember(this.masthead);
		vLayout.addMember(this.applicationMenu);
		northLayout.addMember(vLayout);
		
		// initialise navigation pane
		initNavigationPane();
		
		// initialise West layout container
		westLayout = navigationPane;
		
		// initialise east layout container
		eastLayout = new VLayout();
		
		// initialise the South layout container
		southLayout = new HLayout();
		southLayout.setMembers(westLayout, eastLayout);
		
		// add the North and south layout containers to the main layout container
		mainLayout.addMember(northLayout);
		mainLayout.addMember(southLayout);
		
	}
	
	private void initNavigationPane() {
		companyCategoryTreeGridSlot = new VLayout();
		
		navigationPane.addListgridSection("Main", ToolsNavigationPaneSectionData.getNewRecords());
		
		CategorySectionStackSection companyCategorySection = 
				new CompanyCategorySection("Comapany Category", NameTokens.companydata, companyCategoryTreeGridSlot);
		    
		navigationPane.addCategorySection(companyCategorySection);
		
		CategorySectionStackSection eventCategorySection = 
				new EventCategorySection("Event Category", NameTokens.eventdata,
						eventCategoryTreeGrid);
		
		navigationPane.addCategorySection(eventCategorySection);
		
	}
	
	private void initApplicationMenu() {
	    applicationMenu.addMenu("<u>U</u>ser", DEFAULT_MENU_WIDTH,
	    		"Profile, Log out");
	    applicationMenu.addMenu("<u>H</u>elp", DEFAULT_MENU_WIDTH, 
	    		"How to Use, Report Bug, About");
	}
	
	@Override
	public Widget asWidget() {
		return mainLayout;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		GWT.log("StudentPageView.setInSlot()...", null);
		
		if (slot == StudentPagePresenter.TYPE_SetContextAreaContent) {
			if (content != null) {
				eastLayout.setMembers((VLayout)content);
			}
		} else if (slot == StudentPagePresenter.TYPE_RevealCompanyCategory) {
			if (content != null) {
				companyCategoryTreeGridSlot.setMembers((TreeGrid)content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}
	
	@Override
	public NavigationPane getNavigationPane() {
		return navigationPane;
	}
	
	@Override
	public EventCategoryTreeGrid getEventCategoryTreeGrid() {
		return eventCategoryTreeGrid;
	}

}

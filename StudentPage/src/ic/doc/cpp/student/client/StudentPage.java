package ic.doc.cpp.student.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class StudentPage implements EntryPoint {
	 private static final int NORTH_HEIGHT = 85; // MASTHEAD_HEIGHT + APPLICATION_MENU_HEIGHT
	  
	  private VLayout mainLayout;	
	  private HLayout northLayout;  
	  private HLayout southLayout;
	  private VLayout eastLayout;  
	  private VLayout westLayout;
	  
	  ApplicationMenu applicationMenu ;
	  
	  interface GlobalResources extends ClientBundle {
			@NotStrict
			@Source("StudentPage.css")
			CssResource css();
		  }  
	  
	  public void onModuleLoad() {
		  
		GWT.log("init OnLoadModule()...", null);  
		GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
		
		// get rid of scroll bars, and clear out the window's built-in margin,
	    // because we want to take advantage of the entire client area
	    Window.enableScrolling(false);
	    Window.setMargin("0px");
	    
	    // initialise the main layout container
	    mainLayout = new VLayout();
	    mainLayout.setWidth100();  
	    mainLayout.setHeight100();  
	    
	    // initialise the North layout container
	    northLayout = new HLayout();  
	    northLayout.setHeight(NORTH_HEIGHT); 
	    
	    VLayout vLayout = new VLayout(); 
	    // add the Masthead to the nested layout container
	    vLayout.addMember(new Masthead());
	    
	    // initialise the Application menu
	    applicationMenu = new ApplicationMenu();
	    applicationMenu.addMenu("<u>U</u>ser", 100, "Profile, Log out", new ApplicationMenuClickHandler());
	    applicationMenu.addMenu("<u>H</u>elp", 100, 
								"Help on this Page, Contents, About CPP-Student",
								new ApplicationMenuClickHandler());
	    
	    // add the Application Menu to the nested layout container
	    vLayout.addMember(applicationMenu);
	    
	    // add the nested layout container to the North layout container
	    northLayout.addMember(vLayout);
	    
	    // initialise the Navigation Pane
	    NavigationPane navigationPane = new NavigationPane(new NavigationPaneSectionHeaderClickHandler());
	    CategorySectionStackSection companyCategorySection = new CompanyCategorySection("Comapany Category", 
	    		CompanyCategoryTreeGrid.getInstance(), new CompanyDataView.Factory());
	    
	    navigationPane.addCategorySection(companyCategorySection);
	    
	    CategorySectionStackSection eventCategorySection = new EventCategorySection("Event Category", 
	    		EventCategoryTreeGrid.getInstance(), new EventDataView.Factory());
	    
	    navigationPane.addCategorySection(eventCategorySection);
	    
	    navigationPane.addListgridSection("Tools", ToolsNavigationPaneSectionData.getNewRecords(), 
	    		new NavigationPaneClickHandler());
	    
	    // select the first Navigation Pane section e.g Sales
	    navigationPane.expandSection(0);
	    
	    // initialise the West layout container
	    westLayout = navigationPane;
	    
	    // initialise the East layout container
	    eastLayout = (VLayout) new CompanyDataView.Factory().create(); 
	    
	    // initialise the South layout container
	    southLayout = new HLayout(); 
	 
	    // set the Navigation Pane and Context Area as members of the South 
	    // layout container 
	    southLayout.setMembers(westLayout, eastLayout);  
	    
	    // add the North and South layout containers to the main layout container
	    mainLayout.addMember(northLayout);  
	    mainLayout.addMember(southLayout); 

	    // add the main layout container to GWT's root panel 
	    RootLayoutPanel.get().add(mainLayout);  	  
	  }
	  
	  private class ApplicationMenuClickHandler implements ClickHandler {
		@Override
		public void onClick(MenuItemClickEvent event) {
		  String applicationName = event.getItem().getTitle();
		  SC.say("You clicked: " + applicationName);
		}

	  }
	  
	  private class NavigationPaneSectionHeaderClickHandler implements SectionHeaderClickHandler {

			@Override
			public void onSectionHeaderClick(SectionHeaderClickEvent event) {
				SectionStackSection section = event.getSection();
				if (section instanceof CategorySectionStackSection) {
					section.setExpanded(false);
					ContextAreaFactory factory = ((CategorySectionStackSection)section).getFactory();
					Canvas view = factory.create();
					southLayout.setMembers(westLayout, view);
				}
			}
	  }
	  
	  private class NavigationPaneClickHandler implements RecordClickHandler {
		@Override
		public void onRecordClick(RecordClickEvent event) {
		  ListgridSectionStackSectionRecord record = (ListgridSectionStackSectionRecord) event.getRecord();
		  setContextAreaView(record);
		}
	  }    
	  
	  private void setContextAreaView(ListgridSectionStackSectionRecord record) {
	    ContextAreaFactory factory = record.getFactory();
		Canvas view = factory.create();
		southLayout.setMembers(westLayout, view);   
	  }  
}

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
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class StudentPage implements EntryPoint {
	 private static final int NORTH_HEIGHT = 85; // MASTHEAD_HEIGHT + APPLICATION_MENU_HEIGHT
	  private static final int DEFAULT_MENU_WIDTH = 70;
	  
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
	    applicationMenu.addMenu("User", 100, "Profile, Log out", new ApplicationMenuClickHandler());
//	    applicationMenu.addMenu("<u>N</u>ew Activity", DEFAULT_MENU_WIDTH, 
//	    						"Task, Fax, Phone Call, Email, Letter, " + 
//	    						"Appointment", new ApplicationMenuClickHandler());
//	    applicationMenu.addMenu("New Re<u>c</u>ord", DEFAULT_MENU_WIDTH, 
//								"Account, Contact, separator, Lead, Opportunity",
//								new ApplicationMenuClickHandler());
//	    Menu goToMenu = applicationMenu.addMenu("<u>G</u>o To", 100);
//	    applicationMenu.addSubMenu(goToMenu, "Sales", "Leads, Opportunities, Accounts, Contacts",
//	    						   new ApplicationMenuClickHandler());
//	    applicationMenu.addSubMenu(goToMenu, "Settings", "Administration, Templates, Data Management",
//	    						   new ApplicationMenuClickHandler());
//	    applicationMenu.addSubMenu(goToMenu, "Resource Centre", "Highlights, Sales, Settings",
//	    						   new ApplicationMenuClickHandler());
//	    applicationMenu.addMenu("<u>T</u>ools", DEFAULT_MENU_WIDTH - 30, 
//								"Import Data, Duplicate Detection, Advanced Find, Options",
//								new ApplicationMenuClickHandler());
	    applicationMenu.addMenu("<u>H</u>elp", 100, 
								"Help on this Page, Contents, About CPP-Student",
								new ApplicationMenuClickHandler());
	    // add the Application Menu to the nested layout container
	    vLayout.addMember(applicationMenu);
	    
	    // add the nested layout container to the North layout container
	    northLayout.addMember(vLayout);
	    
	    // initialise the Navigation Pane
	    NavigationPane navigationPane = new NavigationPane();
	    navigationPane.add("Profile", SalesNavigationPaneSectionData.getRecords(), 
	    					new NavigationPaneClickHandler());
	    navigationPane.add("Company Category", SettingsNavigationPaneSectionData.getRecords(), 
						   new NavigationPaneClickHandler());
//	    navigationPane.add("Resource Centre", ResourceCentreNavigationPaneSectionData.getRecords(), 
//				   		   new NavigationPaneClickHandler());
	    
	    // select the first Navigation Pane section e.g Sales
	    navigationPane.expandSection(0);
	    
	    // initialise the West layout container
	    westLayout = navigationPane;
	    
	    // initialise the East layout container
	    eastLayout = new AccountsView(); 
	    
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
	  
	  private class NavigationPaneClickHandler implements RecordClickHandler {
		@Override
		public void onRecordClick(RecordClickEvent event) {
		  NavigationPaneRecord record = (NavigationPaneRecord) event.getRecord();
		  setContextAreaView(record);
		}

	  }    
	  
	  private void setContextAreaView(NavigationPaneRecord record) {
	    ContextAreaFactory factory = record.getFactory();
		Canvas view = factory.create();
		southLayout.setMembers(westLayout, view);   
	  }  
}

package ic.doc.cpp.student.client.core;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuBar;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;

public class ApplicationMenu extends HLayout {

	private static final int APPLICATION_MENU_HEIGHT = 27;
	  private static final int DEFAULT_SHADOW_DEPTH = 10;
	  
	  private static final String SEPARATOR = "separator";
	  
	  private MenuBar menuBar ;
	  private int menuPosition = 0;    
	  	
	  public ApplicationMenu() {
		super();
				
		GWT.log("init ApplicationMenu()...", null);
			
	    // initialise the Application Menu layout container
		setStyleName("crm-ApplicationMenu");	
	    setHeight(APPLICATION_MENU_HEIGHT);
	    setBackgroundColor("#1589FF");
	    
	    // initialise the Menu Bar
		menuBar = new MenuBar();
		
		// add the Menu Bar to the Application Menu layout container
		addMember(menuBar);  	    
	  }
	  
	  public Menu addMenu(String menuName, int width) {
		  // initialise the new menu
		  Menu menu = new Menu(); 
		  menu.setTitle(menuName);
		  menu.setShowShadow(true);  
		  menu.setShadowDepth(DEFAULT_SHADOW_DEPTH); 
		  menu.setWidth(width);
		  
		  Menu[] menus = new Menu[1]; 
		  menus[0] = menu;
		  menuBar.addMenus(menus, menuPosition);	
		  menuPosition++ ;
		  
		  return menu;
	  }
	  
	  public Menu addMenu(String menuName, int width, String menuItemNames) {
		// initialise the new menu
		Menu menu = new Menu(); 
		menu.setTitle(menuName);
		menu.setShowShadow(true);  
		menu.setShadowDepth(DEFAULT_SHADOW_DEPTH); 
		menu.setWidth(width);
		
		// create an array of menu item names 
		String[] menuItems = process(menuItemNames);
		
		for (int i = 0; i < menuItems.length; i++) {
			// remove any whitespace
			String menuItemName = menuItems[i].replaceAll("\\W", "");
			
			if (menuItemName.contentEquals(SEPARATOR)) {
				MenuItemSeparator separator = new MenuItemSeparator();
				menu.addItem(separator);  
				continue;
			}
			  
//			MenuItem menuItem = new MenuItem(menuItems[i], getIcon(menuItems[i])); 
			MenuItem menuItem = new MenuItem(menuItems[i]);
			menu.addItem(menuItem);    
		}
		
		Menu[] menus = new Menu[1]; 
		menus[0] = menu;
		menuBar.addMenus(menus, menuPosition);
		menuPosition++ ;
		
		return menus[0];
	  }
	
		  
	  public Menu addSubMenu(Menu parentMenu, String subMenuName, String menuItemNames) {
		// initialise the new sub menu
		Menu menu = new Menu(); 
		menu.setShowShadow(true);  
		menu.setShadowDepth(DEFAULT_SHADOW_DEPTH); 
				
		MenuItem menuItem = new MenuItem(subMenuName);
		
		// create an array of menu item names 
		String[] menuItems = process(menuItemNames);

		for (int i = 0; i < menuItems.length; i++) {
		  // remove any whitespace
		  String menuItemName = menuItems[i].replaceAll("\\W", "");
				  
		  if (menuItemName.contentEquals(SEPARATOR)) {
		    MenuItemSeparator separator = new MenuItemSeparator();
		    menu.addItem(separator);  
		    continue;
		  }
				  
		  //menuItem = new MenuItem(menuItems[i], getIcon(menuItems[i])); 
		  menuItem = new MenuItem(menuItems[i]); 
		  menu.addItem(menuItem);    
		}
				
		// add the sub menu to the menu item
		menuItem = new MenuItem(subMenuName);
		parentMenu.addItem(menuItem); 
		menuItem.setSubmenu(menu);
				
		return menu;
	  }  
		  
	  public final static String DELIMITER = ",";
		  
	  public static String[] process(String line) {
	    return line.split(DELIMITER);
	  }
		  
//	  private String getIcon(String applicationName) {
//		// remove any whitespace
//		String name = applicationName.replaceAll("\\W", "");
//		// e.g. "icons/16/" + "activities" + ".png"	
//		String icon = ICON_PREFIX + name.toLowerCase() + ICON_SUFFIX ; 
//		return icon ;
//	  }
}

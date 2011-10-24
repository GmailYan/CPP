package ic.doc.cpp.student.client;

import ic.doc.cpp.student.client.data.CompanyCategoryXmlDS;
import ic.doc.cpp.student.client.data.CompanyDetailXmlDS;
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
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class CompanyDataView extends VLayout{
	
	private static final String DESCRIPTION = "CompanyDataView";
	
	private SectionStack dataview;
	//private ItemListGrid itemList;
	private CompanyTileGrid companyTileGrid;
	private Menu itemListMenu;  
	private ItemDeatilTabPane companyDetailTabPane;  
	private CompanySearchForm searchForm;
	private DataSource companyDetailDS, companyCategoryDS;
	private CompanyCategoryTreeGrid categoryTree;
	
	public CompanyDataView() {
		super();
		GWT.log("init CompanyDataView()...", null);
		
		// initialise dataview
		dataview = new SectionStack();
		dataview.setVisibilityMode(VisibilityMode.MULTIPLE);
      	dataview.setAnimateSections(true);
		
      	// initialise datasource				
		companyDetailDS = CompanyDetailXmlDS.getInstance();
		companyCategoryDS = CompanyCategoryXmlDS.getInstance();
		
		// set up CompanyCategoryGridTree
		categoryTree = CompanyCategoryTreeGrid.getInstance();
		categoryTree.addNodeClickHandler(new NodeClickHandler() {  
            public void onNodeClick(NodeClickEvent event) {
            	CheckboxItem findInCategory = searchForm.getFindInCategory();
            	findInCategory.setValue(true);
                findItems();
            }  
        });
		
		// set up Search form
      	searchForm = new CompanySearchForm(companyDetailDS);
      	searchForm.setHeight(60);  
      	searchForm.addFindListener(new ClickHandler() {
      		public void onClick(ClickEvent event) {  
      			findItems();  
      		}  
      	});
      	
      	// set up context menu
      	setupContextMenu();
      	
      	// set up Company TileList
      	companyTileGrid = new CompanyTileGrid(companyDetailDS);
      	companyTileGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {
				companyDetailTabPane.updateDetails();
			}
      		
      	});
      	companyTileGrid.addShowContextMenuHandler(new ShowContextMenuHandler() {
			
			@Override
			public void onShowContextMenu(ShowContextMenuEvent event) {
				itemListMenu.showContextMenu();
				event.cancel();
			}
		});
      	
        // Set up dataview section stack
        SectionStackSection findSection = new SectionStackSection("Find Company");  
        findSection.setItems(searchForm);  
        findSection.setExpanded(true);  
  
        SectionStackSection companyListSection = new SectionStackSection("Company List");  
        companyListSection.setItems(companyTileGrid);
        
        companyListSection.setExpanded(true);  
  
        companyDetailTabPane = new ItemDeatilTabPane(companyDetailDS, companyCategoryDS, companyTileGrid);  
        SectionStackSection companyDetailsSection = new SectionStackSection("Company Details");  
        companyDetailsSection.setItems(companyDetailTabPane);  
        companyDetailsSection.setExpanded(true);  
	    
        dataview.setSections(findSection, companyListSection, companyDetailsSection);  
        
	    // add the List Grid to the CompanyData View layout container
      	this.addMember(dataview);
	}

	  
	private void setupContextMenu() {  
        itemListMenu = new Menu();  
        itemListMenu.setCellHeight(22);  
  
        final MenuItem editMenuItem = new MenuItem("Add to Like List");  
        editMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
            public void onClick(MenuItemClickEvent event) {  
            	SC.say("You like " + companyTileGrid.getSelectedRecord().getAttribute("name"));
            }  
        });
  
        itemListMenu.setData(editMenuItem);
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
        	companyTileGrid.filterData(findValues);
        
    	companyDetailTabPane.clearDetails(categoryTree.getSelectedRecord());
        
    }  
	
	public static class Factory implements ContextAreaFactory {
		
		private String id;
		public static Canvas view;
		
		public Canvas create() {
			if (view == null)
				view = new CompanyDataView();
			id = view.getID();
			
			GWT.log("CompanyDataView.Factory.create()->view.getID() - " + id, null);
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

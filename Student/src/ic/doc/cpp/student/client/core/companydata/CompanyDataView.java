package ic.doc.cpp.student.client.core.companydata;

import ic.doc.cpp.student.client.core.CompanyCategoryTreeGrid;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;

public class CompanyDataView extends ViewImpl implements
		CompanyDataPresenter.MyView {
	
	private VLayout widget;
	private SectionStack dataview;
	private CompanyTileGrid companyTileGrid;
	private Menu companyListMenu;  
	private CompanyDetailViewer companyDetailTabPane;  
	private CompanySearchForm searchForm;
	private CompanyCategoryTreeGrid categoryTree;
	
	private SectionStackSection findSection;
	
	@Inject
	public CompanyDataView() {
		GWT.log("init CompanyDataView()...");
		
		// initialise main container
		widget = new VLayout();
		
		// initialise dataview
		dataview = new SectionStack();
		dataview.setVisibilityMode(VisibilityMode.MULTIPLE);
		dataview.setAnimateSections(true);
		
		// initialise CompanyCategoryGridTree
		categoryTree = CompanyCategoryTreeGrid.getInstance();
		
		// set up Search form
      	searchForm = new CompanySearchForm();
      	searchForm.setHeight(60);
      	
      	// set up context menu
      	setupContextMenu();
      	
      	// initialise Company TileList
      	companyTileGrid = new CompanyTileGrid();
      	
      	// initialise detail viewer
      	companyDetailTabPane = new CompanyDetailViewer(companyTileGrid);
      	
      	// Set up dataview section stack
        findSection = new SectionStackSection("Find Company");  
        findSection.setItems(searchForm);  
        findSection.setExpanded(true);  
        SectionStackSection companyListSection = new SectionStackSection("Company List");  
        companyListSection.setItems(companyTileGrid);
        
        companyListSection.setExpanded(true);  
  
        SectionStackSection companyDetailsSection = new SectionStackSection("Company Details");  
        companyDetailsSection.setItems(companyDetailTabPane);  
        companyDetailsSection.setExpanded(true);  
	    
        dataview.setSections(findSection, companyListSection, companyDetailsSection);
        
        // add the List Grid to the CompanyData View layout container
        widget.addMember(dataview);
	}
	
	private void setupContextMenu() {  
        companyListMenu = new Menu();  
        companyListMenu.setCellHeight(22);  
        final MenuItem likeMenuItem = new MenuItem("Add to Like List");  
        companyListMenu.setData(likeMenuItem);
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
	public CompanyTileGrid getCompanyTileGrid() {
		return companyTileGrid;
	}
	
	@Override
	public Menu getItemListMenu() {
		return companyListMenu;
	}
	
	@Override
	public CompanyDetailViewer getCompanyDetailTabPane() {
		return companyDetailTabPane;
	}
	
	@Override
	public CompanySearchForm getSearchForm() {
		return searchForm;
	}
	
	@Override
	public CompanyCategoryTreeGrid getCategoryTree() {
		return categoryTree;
	}

}

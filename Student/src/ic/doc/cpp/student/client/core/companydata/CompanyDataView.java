package ic.doc.cpp.student.client.core.companydata;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;

public class CompanyDataView extends ViewImpl implements
		CompanyDataPresenter.MyView {
	
	private VLayout widget;
	private SectionStack dataviewSectionStack;
	private Menu companyListMenu;  
	private VLayout searchFormSlot, companyTileGridSlot, companyDetailTabSetSlot;
	
	@Inject
	public CompanyDataView() {
		GWT.log("initialise CompanyDataView()...");
		
		// initialise main container
		widget = new VLayout();
		
		// initialise dataview
		dataviewSectionStack = new SectionStack();
		dataviewSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		dataviewSectionStack.setAnimateSections(true);
		
      	// set up context menu
      	setupContextMenu();
      	
      	// Set up dataview section stack section - search form section
      	SectionStackSection findSection = new SectionStackSection("Find Company");
      	searchFormSlot = new VLayout();
      	searchFormSlot.setHeight(20);
      	findSection.setItems(searchFormSlot);
      	findSection.setExpanded(true);  
        
      	// Set up dataview section stack section - CompanyTileGrid section
        SectionStackSection companyListSection = new SectionStackSection("Company List");  
        companyTileGridSlot = new VLayout();
        companyListSection.setItems(companyTileGridSlot);
        companyListSection.setExpanded(true);  
  
        // Set up dataview section stack section - CompanyDetailTabset section
        SectionStackSection companyDetailsSection = new SectionStackSection("Company Details");
        companyDetailTabSetSlot = new VLayout();
        companyDetailsSection.setItems(companyDetailTabSetSlot);
        companyDetailsSection.setExpanded(true);  
	    
        dataviewSectionStack.setSections(findSection, companyListSection, companyDetailsSection);
        
        // add the List Grid to the CompanyData View layout container
        widget.addMember(dataviewSectionStack);
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
	public SectionStack getDataviewSectionStack() {
		return dataviewSectionStack;
	}
	
	@Override
	public Menu getItemListMenu() {
		return companyListMenu;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		GWT.log("CompanyDataView.setInSlot()...", null);
		
		if (slot == CompanyDataPresenter.TYPE_RevealCompanySearchForm) {
			if (content != null) {
				searchFormSlot.addMember(content);
			}
		} else if (slot == CompanyDataPresenter.TYPE_RevealCompanyTileGrid) {
			if (content != null) {
				companyTileGridSlot.addMember(content);
			}
		} else if (slot == CompanyDataPresenter.TYPE_RevealCompanyDetailTabSet) {
			if (content != null) {
				companyDetailTabSetSlot.addMember(content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public HandlerRegistration addLikeMenuItemClickHandler(
			ClickHandler clickHandler) {
		return getItemListMenu().getItem(0).addClickHandler(clickHandler);
	}
}

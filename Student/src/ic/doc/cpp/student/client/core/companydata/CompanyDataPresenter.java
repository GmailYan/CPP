package ic.doc.cpp.student.client.core.companydata;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.student.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import ic.doc.cpp.student.client.core.CompanyCategoryWidgetPresenter;
import ic.doc.cpp.student.client.core.StudentPagePresenter;

public class CompanyDataPresenter extends
		Presenter<CompanyDataPresenter.MyView, CompanyDataPresenter.MyProxy> {
	
	private final CompanySearchFormWidgetPresenter searchForm;
	private final CompanyTileGridWidgetPresenter companyTileGrid;
	private final CompanyDetailTabSetWidgetPresenter companyDetailTabset;
	private final CompanyCategoryWidgetPresenter companyCategory;
	
	public static final Object TYPE_RevealCompanySearchForm = new Object();
	public static final Object TYPE_RevealCompanyTileGrid = new Object();
	public static final Object TYPE_RevealCompanyDetailTabSet = new Object();
	
	public interface MyView extends View {
		public Menu getItemListMenu();
		SectionStack getDataviewSectionStack();
	}
	
	
	@ProxyCodeSplit
	@NameToken(NameTokens.companydata)
	public interface MyProxy extends ProxyPlace<CompanyDataPresenter> {
	}

	@Inject
	public CompanyDataPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final CompanySearchFormWidgetPresenter searchForm,
			final CompanyTileGridWidgetPresenter companyTileGrid,
			final CompanyDetailTabSetWidgetPresenter companyDetailTabset,
			final CompanyCategoryWidgetPresenter companyCategory) {
		super(eventBus, view, proxy);
		this.searchForm = searchForm;
		this.companyTileGrid = companyTileGrid;
		this.companyDetailTabset = companyDetailTabset;
		this.companyCategory = companyCategory;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(companyCategory.getView().addNodeClickHandler(new NodeClickHandler() {
			
			@Override
			public void onNodeClick(NodeClickEvent event) {
				CheckboxItem findInCategory = searchForm.getView().getFindInCategory();
            	findInCategory.setValue(true);
                findItems();
			}
		}));
		
		registerHandler(searchForm.getView().addFindListener(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				findItems();
			}
		}));
		
		registerHandler(companyTileGrid.getView().addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {
				companyDetailTabset.updateDetails();
			}
      		
      	}));
		
		registerHandler(companyTileGrid.getView().addShowContextMenuHandler(new ShowContextMenuHandler() {
			
			@Override
			public void onShowContextMenu(ShowContextMenuEvent event) {
				getView().getItemListMenu().showContextMenu();
				event.cancel();
			}
		}));
		
		registerHandler(getView().getItemListMenu().getItem(0).addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				SC.say("You like " + companyTileGrid.getView().getSelectedRecord().getAttribute("name"));
			}
		}));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		setInSlot(TYPE_RevealCompanySearchForm, searchForm);
		setInSlot(TYPE_RevealCompanyTileGrid, companyTileGrid);
		setInSlot(TYPE_RevealCompanyDetailTabSet, companyDetailTabset);
	}
	
	public void findItems() {
        Criteria findValues = null;  
        String categoryName = "";
        String useCategoryTreeValue = searchForm.getView().getValueAsString("findInCategory");
        ListGridRecord selectedCategory = companyCategory.getView().getSelectedRecord();

        if (useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	categoryName = selectedCategory.getAttribute("categoryName");  
            findValues = searchForm.getView().getValuesAsCriteria();
            findValues.addCriteria("category", categoryName);  
        } else if (!useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	findValues = searchForm.getView().getValuesAsCriteria();  
            findValues.addCriteria("category", categoryName); 
        } else {
        	SC.say("Please select a category to use category!");
        }
        
        if (findValues != null)
        	companyTileGrid.getView().filterData(findValues);
        
    	companyDetailTabset.clearDetails();
        
    }  
}

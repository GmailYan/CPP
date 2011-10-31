package ic.doc.cpp.student.client.core.companydata;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.student.client.place.NameTokens;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
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

import ic.doc.cpp.student.client.core.CompanyCategoryTreeGrid;
import ic.doc.cpp.student.client.core.StudentPagePresenter;

public class CompanyDataPresenter extends
		Presenter<CompanyDataPresenter.MyView, CompanyDataPresenter.MyProxy> {
	
	private final PlaceManager placeManager;
	
	public interface MyView extends View {
		public SectionStack getDataview();
		public CompanyTileGrid getCompanyTileGrid();
		public Menu getItemListMenu();
		public CompanyDetailViewer getCompanyDetailTabPane();
		public CompanySearchForm getSearchForm();
		CompanyCategoryTreeGrid getCategoryTree();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.companydata)
	public interface MyProxy extends ProxyPlace<CompanyDataPresenter> {
	}

	@Inject
	public CompanyDataPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().getCategoryTree().addNodeClickHandler(new NodeClickHandler() {
			
			@Override
			public void onNodeClick(NodeClickEvent event) {
				CheckboxItem findInCategory = getView().getSearchForm().getFindInCategory();
            	findInCategory.setValue(true);
                findItems();
			}
		}));
		
		registerHandler(getView().getSearchForm().addFindListener(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				findItems();
			}
		}));
		
		registerHandler(getView().getCompanyTileGrid().addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {
				getView().getCompanyDetailTabPane().updateDetails();
			}
      		
      	}));
		
		registerHandler(getView().getCompanyTileGrid().addShowContextMenuHandler(new ShowContextMenuHandler() {
			
			@Override
			public void onShowContextMenu(ShowContextMenuEvent event) {
				getView().getItemListMenu().showContextMenu();
				event.cancel();
			}
		}));
		
		registerHandler(getView().getItemListMenu().getItem(0).addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				SC.say("You like " + getView().getCompanyTileGrid().getSelectedRecord().getAttribute("name"));
			}
		}));
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}
	
	public void findItems() {
        Criteria findValues = null;  
        String categoryName = "";
        String useCategoryTreeValue = getView().getSearchForm().getValueAsString("findInCategory");
        ListGridRecord selectedCategory = getView().getCategoryTree().getSelectedRecord();

        if (useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	categoryName = selectedCategory.getAttribute("categoryName");  
            findValues = getView().getSearchForm().getValuesAsCriteria();
            findValues.addCriteria("category", categoryName);  
        } else if (!useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	findValues = getView().getSearchForm().getValuesAsCriteria();  
            findValues.addCriteria("category", categoryName); 
        } else {
        	SC.say("Please select a category to use category!");
        }
        
        if (findValues != null)
        	getView().getCompanyTileGrid().filterData(findValues);
        
    	getView().getCompanyDetailTabPane().clearDetails(getView().getCategoryTree().getSelectedRecord());
        
    }  
}

package ic.doc.cpp.student.client.core.companydata;


import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;

import ic.doc.cpp.student.client.LoggedInGatekeeper;
import ic.doc.cpp.student.client.place.NameTokens;
import ic.doc.cpp.student.client.util.CreateRecordFromDto;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.data.Record;
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
import ic.doc.cpp.student.shared.action.AddStudentInterestedCompany;
import ic.doc.cpp.student.shared.action.AddStudentInterestedCompanyResult;
import ic.doc.cpp.student.shared.action.RetrieveCompanysUsingNameAndCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanysUsingNameAndCategoryResult;

public class CompanyDataPresenter extends
		Presenter<CompanyDataPresenter.MyView, CompanyDataPresenter.MyProxy> {
	
	private final DispatchAsync dispatcher;
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
		public HandlerRegistration addLikeMenuItemClickHandler(
				com.smartgwt.client.widgets.menu.events.ClickHandler clickHandler);
	}
	
	
	@ProxyCodeSplit
	@NameToken(NameTokens.companydata)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<CompanyDataPresenter> {
	}

	@Inject
	public CompanyDataPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher,
			final CompanySearchFormWidgetPresenter searchForm,
			final CompanyTileGridWidgetPresenter companyTileGrid,
			final CompanyDetailTabSetWidgetPresenter companyDetailTabset,
			final CompanyCategoryWidgetPresenter companyCategory) {
		super(eventBus, view, proxy);
		this.searchForm = searchForm;
		this.companyTileGrid = companyTileGrid;
		this.companyDetailTabset = companyDetailTabset;
		this.companyCategory = companyCategory;
		this.dispatcher = dispatcher;
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
		
		registerHandler(getView().addLikeMenuItemClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				Long companyId = companyTileGrid.getSelectedCompanyId();
				if (companyId != null) {
					dispatcher.execute(new AddStudentInterestedCompany(companyId), 
							new AsyncCallback<AddStudentInterestedCompanyResult>() {
	
						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Fail on addStudentInterestedCompany() - " + caught.getLocalizedMessage());
						}
	
						@Override
						public void onSuccess(
								AddStudentInterestedCompanyResult result) {
							SC.say(result.getMessage());
						}
					});
				}
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
	
	@Override
	protected void onReset() {
		super.onReset();
	}

	public void findItems() {
		String companyName = null;
        String categoryName = null;
        
        String findInCategory = searchForm.getView().getValueAsString("findInCategory");
        ListGridRecord selectedCategory = companyCategory.getView().getSelectedRecord();

        if (findInCategory.equals("true") && selectedCategory != null) {
        	categoryName = selectedCategory.getAttribute("categoryActuralName");  
        	companyName = searchForm.getView().getValueAsString("name");
        } else if (!findInCategory.equals("true")) {
        	companyName = searchForm.getView().getValueAsString("name");
        } else {
        	SC.say("Please select a category to use category!");
        }
        
        dispatcher.execute(
        		new RetrieveCompanysUsingNameAndCategory(companyName, categoryName), 
        		new AsyncCallback<RetrieveCompanysUsingNameAndCategoryResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Fail on RetrieveCompanysUsingNameAndCategory()...");
					}

					@Override
					public void onSuccess(
							RetrieveCompanysUsingNameAndCategoryResult result) {
						if (result != null) {
							companyTileGrid.getView().setData(
									CreateRecordFromDto.createCompanyTileRecordsFromCompanyDtos(
											result.getCompanyDtos()).toArray(new Record[0]));
						}
					}
		});
        
    	companyDetailTabset.clearDetails();
        
    }  
}

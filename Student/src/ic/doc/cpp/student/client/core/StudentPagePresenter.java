package ic.doc.cpp.student.client.core;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;

import ic.doc.cpp.student.client.LoggedInGatekeeper;
import ic.doc.cpp.student.client.place.NameTokens;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

public class StudentPagePresenter extends
		Presenter<StudentPagePresenter.MyView, StudentPagePresenter.MyProxy> {
	
	private final PlaceManager placeManager;
	private final CompanyCategoryWidgetPresenter companyCategory; 
	
	public static final Object TYPE_RevealCompanyCategory = new Object(); 
	
	public interface MyView extends View {
		public NavigationPane getNavigationPane();

		EventCategoryTreeGrid getEventCategoryTreeGrid();
	}
	
	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

	@ProxyCodeSplit
	@NameToken(NameTokens.studentpage)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<StudentPagePresenter> {
	}

	@Inject
	public StudentPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager,
			final CompanyCategoryWidgetPresenter companyCategory) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.companyCategory = companyCategory;
	}

	@Override
	protected void revealInParent() {
		RevealRootLayoutContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().getNavigationPane().addRecordClickHandler("Main", new NavigationPaneRecordClickHandler()));
		
		registerHandler(getView().getNavigationPane().addSectionHeaderClickHandler(new NavigationPaneSectionHeaderClickHandler()));
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getView().getNavigationPane().expandSection(0);
		
		setInSlot(TYPE_RevealCompanyCategory, companyCategory);
		
		PlaceRequest myRequset = new PlaceRequest(NameTokens.eventfeed);
		placeManager.revealPlace(myRequset);
	}
	
	public class NavigationPaneRecordClickHandler implements RecordClickHandler {

		@Override
		public void onRecordClick(RecordClickEvent event) {
			Record record = event.getRecord();
			String name = record.getAttributeAsString("place");
			GWT.log("NavigationPaneRecordClickHandler.onRecordClick() - " + name, null);
			PlaceRequest myRequest = new PlaceRequest(name);
			placeManager.revealPlace(myRequest);
					
		}
		
	}
	
	public class NavigationPaneSectionHeaderClickHandler implements SectionHeaderClickHandler {

		@Override
		public void onSectionHeaderClick(SectionHeaderClickEvent event) {
			SectionStackSection section = event.getSection();
			section.setExpanded(false);
			if (section instanceof CategorySectionStackSection) {
				String contextAreaName = ((CategorySectionStackSection) section).getContextAreaName();
				GWT.log("NavigationPaneSecitonHeaderClickHandler.onSectionHeaderClick() - " + contextAreaName, null);
				PlaceRequest myRequest = new PlaceRequest(contextAreaName);
				placeManager.revealPlace(myRequest);
			} else {
//				placeManager.revealPlace();
			}
		}
		
	}
}

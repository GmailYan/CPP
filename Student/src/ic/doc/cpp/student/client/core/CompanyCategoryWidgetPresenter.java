package ic.doc.cpp.student.client.core;

import java.util.List;

import ic.doc.cpp.student.shared.action.RetrieveCompanyCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategoryResult;
import ic.doc.cpp.student.shared.dto.CompanyCategoryDto;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class CompanyCategoryWidgetPresenter extends
		PresenterWidget<CompanyCategoryWidgetPresenter.MyView> {
	
	private final DispatchAsync dispatcher;
	
	public interface MyView extends View {

		void setData(List<CompanyCategoryDto> list);

		HandlerRegistration addNodeClickHandler(
				NodeClickHandler nodeClickHandler);

		ListGridRecord getSelectedRecord();
		
	}

	@Inject
	public CompanyCategoryWidgetPresenter(final EventBus eventBus, final MyView view,
			DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
	
	@Override
	protected void onReset() {
		super.onReset();
		retrieveCategoryData();
	}

	private void retrieveCategoryData() {
		dispatcher.execute(new RetrieveCompanyCategory(), 
				new AsyncCallback<RetrieveCompanyCategoryResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Fail on retrieveCompanyCategoryData() - " + caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(RetrieveCompanyCategoryResult result) {
						getView().setData(result.getCompanyCategoryDtos());
					}
			
		});
	}

}

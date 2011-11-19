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
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class CompanyCategoryWidgetPresenter extends
		PresenterWidget<CompanyCategoryWidgetPresenter.MyView> {
	
	private final DispatchAsync dispatcher;
	
	public interface MyView extends View {

		HandlerRegistration addNodeClickHandler(
				NodeClickHandler nodeClickHandler);

		ListGridRecord getSelectedRecord();

		void setData(Tree tree);
		
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
						Tree tree = constructCategoryTree(result.getCompanyCategoryDtos());
						getView().setData(tree);
					}
			
		});
	}
	
	protected Tree constructCategoryTree(
			List<CompanyCategoryDto> companyCategoryDtos) {
		Tree tree = new Tree();
	    tree.setModelType(TreeModelType.PARENT);
	    tree.setNameProperty("categoryName");
	    tree.setIdField("categoryId");
	    tree.setParentIdField("parentId");
	    
	    TreeNode root = new CompanyCategoryTreeNode(0L, "All", "All", null);
	    
	    TreeNode[] nodes = new TreeNode[companyCategoryDtos.size() + 1];
	    nodes[companyCategoryDtos.size()] = root;
	    
		for (int i = 0; i < companyCategoryDtos.size(); i++) {
			CompanyCategoryDto companyCategoryDto = companyCategoryDtos.get(i);
			String name = companyCategoryDto.getCategoryName();
			String display = name.substring(name.lastIndexOf("/") + 1);
			nodes[i] = new CompanyCategoryTreeNode(companyCategoryDto.getCategoryId(),
					display, name, companyCategoryDto.getParentId());
		}

		tree.setData(nodes);
		return tree;
	}

	

}

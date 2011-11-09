package ic.doc.cpp.student.client.core;

import ic.doc.cpp.student.shared.dto.CompanyCategoryDto;

import java.util.List;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class CompanyCategoryWidgetView extends ViewImpl implements
		CompanyCategoryWidgetPresenter.MyView {

	private TreeGrid widget;
	
	
	@Inject
	public CompanyCategoryWidgetView() {
		widget = new TreeGrid();
		widget.setShowHeader(false);  
		widget.setLeaveScrollbarGap(false);  
		widget.setAnimateFolders(true);  
		widget.setCanAcceptDroppedRecords(true);  
		widget.setCanReparentNodes(false);  
		widget.setSelectionType(SelectionStyle.SINGLE);
		
	}
	
	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setData(List<CompanyCategoryDto> list) {
		final Tree tree;
		tree = new Tree();
	    tree.setModelType(TreeModelType.PARENT);
	    tree.setNameProperty("categoryName");
	    tree.setIdField("categoryId");
	    tree.setParentIdField("parentId");
	    
	    TreeNode root = new CompanyCategoryTreeNode(0L, "All", "All", null);
	    
	    TreeNode[] nodes = new TreeNode[list.size() + 1];
	    nodes[list.size()] = root;
	    
		for (int i = 0; i < list.size(); i++) {
			CompanyCategoryDto companyCategoryDto = list.get(i);
			String name = companyCategoryDto.getCategoryName();
			String display = name.substring(name.lastIndexOf("/") + 1);
			nodes[i] = new CompanyCategoryTreeNode(companyCategoryDto.getCategoryId(),
					display, name, companyCategoryDto.getParentId());
		}

		tree.setData(nodes);
		widget.setData(tree);
	}

	@Override
	public HandlerRegistration addNodeClickHandler(
			NodeClickHandler nodeClickHandler) {
		return widget.addNodeClickHandler(nodeClickHandler);
	}

	@Override
	public ListGridRecord getSelectedRecord() {
		return widget.getSelectedRecord();
	}
}

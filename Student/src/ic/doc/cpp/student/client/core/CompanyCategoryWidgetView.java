package ic.doc.cpp.student.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
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
	public void setData(Tree tree) {
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

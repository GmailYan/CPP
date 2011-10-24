package ic.doc.cpp.student.client;

import ic.doc.cpp.student.client.data.EventCategoryXmlDS;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class EventCategoryTreeGrid extends TreeGrid {
	
	private static EventCategoryTreeGrid grid;
	
	public static EventCategoryTreeGrid getInstance() {
		if (grid == null)
			grid = new EventCategoryTreeGrid();
		return grid;
	}
	
	private EventCategoryTreeGrid() {  
		setShowHeader(false);  
		setLeaveScrollbarGap(false);  
		setAnimateFolders(true);  
		setCanAcceptDroppedRecords(true);  
		setCanReparentNodes(false);  
		setSelectionType(SelectionStyle.SINGLE);  
		
		setDataSource(EventCategoryXmlDS.getInstance());
	}
}

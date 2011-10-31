package ic.doc.cpp.student.client.core;

import ic.doc.cpp.student.client.core.data.CompanyCategoryXmlDS;

import com.smartgwt.client.types.SelectionStyle;  
import com.smartgwt.client.widgets.tree.TreeGrid;  
  
public class CompanyCategoryTreeGrid extends TreeGrid {  
	private static CompanyCategoryTreeGrid grid;
	
	public static CompanyCategoryTreeGrid getInstance() {
		if (grid == null)
			grid = new CompanyCategoryTreeGrid();
		return grid;
	}
	
    private CompanyCategoryTreeGrid() {  
        setShowHeader(false);  
        setLeaveScrollbarGap(false);  
        setAnimateFolders(true);  
        setCanAcceptDroppedRecords(true);  
        setCanReparentNodes(false);  
        setSelectionType(SelectionStyle.SINGLE);
        setDataSource(CompanyCategoryXmlDS.getInstance());
    }
} 
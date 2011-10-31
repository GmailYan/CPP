package ic.doc.cpp.student.client.core;

import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class CategorySectionStackSection extends SectionStackSection {
	protected TreeGrid grid;
	protected String contextAreaName;
	
	public CategorySectionStackSection(String sectionTitle, String contextAreaName, TreeGrid grid) {
		super(sectionTitle);
		this.contextAreaName = contextAreaName;
		this.grid = grid;
	}

	public TreeGrid getGrid() {
		return grid;
	}

	public void setGrid(TreeGrid grid) {
		this.grid = grid;
	}

	public String getContextAreaName() {
		return contextAreaName;
	}
	
	
}

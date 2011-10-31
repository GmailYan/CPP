package ic.doc.cpp.student.client.core;

public class CompanyCategorySection extends CategorySectionStackSection {
	public CompanyCategorySection(String sectionTitle, String contextAreaName, CompanyCategoryTreeGrid grid) {
		  super(sectionTitle, contextAreaName, grid);
		  grid.setAutoFetchData(true);
		  this.addItem(grid);
		  this.setExpanded(true);
	}
}

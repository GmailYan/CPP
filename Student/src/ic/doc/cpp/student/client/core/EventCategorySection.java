package ic.doc.cpp.student.client.core;

public class EventCategorySection extends CategorySectionStackSection {
	public EventCategorySection(String sectionTitle, String contextAreaName, EventCategoryTreeGrid grid) {
		  super(sectionTitle, contextAreaName, grid);
		  grid.setAutoFetchData(true);
		  this.addItem(grid);
		  this.setExpanded(true); 
	}
}

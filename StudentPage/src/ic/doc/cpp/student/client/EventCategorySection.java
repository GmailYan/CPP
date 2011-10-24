package ic.doc.cpp.student.client;

public class EventCategorySection extends CategorySectionStackSection {
	public EventCategorySection(String sectionName, EventCategoryTreeGrid item, ContextAreaFactory factory) {
		  super(sectionName, factory);
		  item.setAutoFetchData(true);
		  this.addItem(item);
		  this.setExpanded(true); 
	}
}

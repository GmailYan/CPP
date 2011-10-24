package ic.doc.cpp.student.client;

public class CompanyCategorySection extends CategorySectionStackSection {
	public CompanyCategorySection(String sectionName, CompanyCategoryTreeGrid item, ContextAreaFactory factory) {
		  super(sectionName, factory);
		  item.setAutoFetchData(true);
		  this.addItem(item);
		  this.setExpanded(true);
		  this.factory = factory;
	}
}

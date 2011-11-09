package ic.doc.cpp.student.client.core;

import com.smartgwt.client.widgets.layout.VLayout;

public class CompanyCategorySection extends CategorySectionStackSection {
	public CompanyCategorySection(String sectionTitle, String contextAreaName, VLayout slot) {
		  super(sectionTitle, contextAreaName);
		  addItem(slot);
		  setExpanded(true);
	}
}

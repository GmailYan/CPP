package ic.doc.cpp.student.client;

import com.smartgwt.client.widgets.layout.SectionStackSection;

public class CategorySectionStackSection extends SectionStackSection {
	protected ContextAreaFactory factory;
	
	public CategorySectionStackSection(String sectionName, ContextAreaFactory factory) {
		super(sectionName);
		this.factory = factory;
	}

	public ContextAreaFactory getFactory() {
		return factory;
	}

	public void setFactory(ContextAreaFactory factory) {
		this.factory = factory;
	}
	
	
}

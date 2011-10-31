package ic.doc.cpp.student.client.core.companydata;

import ic.doc.cpp.student.client.core.data.CompanyDetailXmlDS;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class CompanySearchForm extends SearchForm {
	private ButtonItem findCompanyButton;
    private CheckboxItem findInCategory;

	public CompanySearchForm() {
	
	    setTop(20);
	    setCellPadding(6);
	    setNumCols(7);
	    setStyleName("defaultBorder");
	    
		setDataSource(CompanyDetailXmlDS.getInstance());
	    
	    findCompanyButton = new ButtonItem("Find");
	    //findItem.setIcon("silk/find.png");
	    findCompanyButton.setWidth(70);
	    findCompanyButton.setEndRow(false);
	
	    TextItem companyName = new TextItem("name", "Name");
	    
	    findInCategory = new CheckboxItem("findInCategory");
	    findInCategory.setTitle("Use Category");
	    findInCategory.setDefaultValue(true);
	    findInCategory.setShouldSaveValue(false);
	
	    setItems(findCompanyButton, companyName, findInCategory);
	}
	
	public CheckboxItem getFindInCategory() {
		return findInCategory;
	}
	
	public HandlerRegistration addFindListener(ClickHandler handler) {
	    return findCompanyButton.addClickHandler(handler);
	}
}

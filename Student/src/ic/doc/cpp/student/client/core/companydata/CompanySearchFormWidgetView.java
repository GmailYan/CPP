package ic.doc.cpp.student.client.core.companydata;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class CompanySearchFormWidgetView extends ViewImpl implements
		CompanySearchFormWidgetPresenter.MyView {
	
	private SearchForm searchForm;
	private ButtonItem findCompanyButton;
    private CheckboxItem findInCategory;
	
	@Inject
	public CompanySearchFormWidgetView() {
		GWT.log("initialise CompanySearchForm()...");
		
		searchForm = new SearchForm();
		searchForm.setTop(20);
		searchForm.setCellPadding(6);
		searchForm.setNumCols(7);
		searchForm.setStyleName("defaultBorder");
		searchForm.setHeight(20);
		searchForm.setBorder("0px");
	}

	@Override
	public Widget asWidget() {
		
		return searchForm;
	}

	@Override
	public void setDataSource(DataSource ds) {
		searchForm.setDataSource(ds);
	    
	    findCompanyButton = new ButtonItem("Find");
	    //findItem.setIcon("silk/find.png");
	    findCompanyButton.setWidth(70);
	    findCompanyButton.setEndRow(false);
	
	    TextItem companyName = new TextItem("name", "Name");
	    
	    findInCategory = new CheckboxItem("findInCategory");
	    findInCategory.setTitle("Use Category");
	    findInCategory.setDefaultValue(true);
	    findInCategory.setShouldSaveValue(false);
	
	    searchForm.setItems(findCompanyButton, companyName, findInCategory);
	}

	
	@Override
	public CheckboxItem getFindInCategory() {
		return findInCategory;
	}
	
	@Override
	public HandlerRegistration addFindListener(ClickHandler handler) {
	    return findCompanyButton.addClickHandler(handler);
	}

	@Override
	public String getValueAsString(String fieldName) {
		return searchForm.getValueAsString(fieldName);
	}

	@Override
	public Criteria getValuesAsCriteria() {
		return searchForm.getValuesAsCriteria();
	}
	
}

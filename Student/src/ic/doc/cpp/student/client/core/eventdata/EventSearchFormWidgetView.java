package ic.doc.cpp.student.client.core.eventdata;

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

public class EventSearchFormWidgetView extends ViewImpl implements
		EventSearchFormWidgetPresenter.MyView {
	
	private SearchForm widget;
	private ButtonItem findItem;
    private CheckboxItem findInCategory;

	@Inject
	public EventSearchFormWidgetView() {
		GWT.log("init EvetntSearchForm()...");
		
		// initialise searchform
		widget = new SearchForm();
		widget.setTop(20);
		widget.setCellPadding(6);
		widget.setNumCols(7);
		widget.setStyleName("defaultBorder");
		widget.setHeight(20);
		
	    findItem = new ButtonItem("Find");
	    //findItem.setIcon("silk/find.png");
	    findItem.setWidth(70);
	    findItem.setEndRow(false);
	
	    TextItem eventTitle = new TextItem("title", "Title");
	    
	    findInCategory = new CheckboxItem("findInCategory");
	    findInCategory.setTitle("Use Category");
	    findInCategory.setDefaultValue(true);
	    findInCategory.setShouldSaveValue(false);
	
	    widget.setItems(findItem, eventTitle, findInCategory);
	}
	
	@Override
	public CheckboxItem getFindInCategory() {
		return findInCategory;
	}
	
	@Override
	public HandlerRegistration addFindListener(ClickHandler handler) {
	    return findItem.addClickHandler(handler);
	}
	
	@Override
	public void setDataSource(DataSource ds) {
		widget.setDataSource(ds);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public Criteria getValuesAsCriteria() {
		return widget.getValuesAsCriteria();
	}
	
	@Override
	public String getValueAsString(String field) {
		return widget.getValueAsString(field);
	}
}

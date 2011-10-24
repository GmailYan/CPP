package ic.doc.cpp.student.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class EventSearchForm extends SearchForm {
	private ButtonItem findItem;
    private CheckboxItem findInCategory;

	public EventSearchForm(DataSource supplyItemDS) {
	
	    setDataSource(supplyItemDS);
	    setTop(20);
	    setCellPadding(6);
	    setNumCols(7);
	    setStyleName("defaultBorder");
	
	    findItem = new ButtonItem("Find");
	    //findItem.setIcon("silk/find.png");
	    findItem.setWidth(70);
	    findItem.setEndRow(false);
	
	    TextItem eventTitle = new TextItem("title", "Title");
	    
	    findInCategory = new CheckboxItem("findInCategory");
	    findInCategory.setTitle("Use Category");
	    findInCategory.setDefaultValue(true);
	    findInCategory.setShouldSaveValue(false);
	
	    setItems(findItem, eventTitle, findInCategory);
	}
	
	public CheckboxItem getFindInCategory() {
		return findInCategory;
	}
	
	public void addFindListener(ClickHandler handler) {
	    findItem.addClickHandler(handler);
	}
}

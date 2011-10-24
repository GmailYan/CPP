package ic.doc.cpp.student.client;

import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.Record;  
import com.smartgwt.client.types.Alignment;  
import com.smartgwt.client.widgets.Label;  
import com.smartgwt.client.widgets.form.DynamicForm;  
import com.smartgwt.client.widgets.form.fields.*;  
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;  
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;  
import com.smartgwt.client.widgets.tab.Tab;  
import com.smartgwt.client.widgets.tab.TabSet;  
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;  
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;  
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewer;  
  
import java.util.HashMap;  
import java.util.Map;  
  
public class ItemDeatilTabPane extends TabSet {  
  
    private DetailViewer itemViewer;  
    private DynamicForm editorForm;  
    private Label editorLabel;  
    private TileGrid companyTileGrid;  
  
    public ItemDeatilTabPane(DataSource supplyItemDS, DataSource supplyCategoryDS, TileGrid companyTileGrid) {  
        this.companyTileGrid = companyTileGrid;  
        itemViewer = new DetailViewer();  
        itemViewer.setDataSource(supplyItemDS);  
        itemViewer.setWidth100();  
        itemViewer.setMargin(25);  
        itemViewer.setEmptyMessage("Select an item to view its details");  
  
        editorLabel = new Label();  
        editorLabel.setWidth100();  
        editorLabel.setHeight100();  
        editorLabel.setAlign(Alignment.CENTER);  
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");  
  
        editorForm = new DynamicForm();  
        editorForm.setWidth(650);  
        editorForm.setMargin(25);  
        editorForm.setNumCols(4);  
        editorForm.setCellPadding(5);  
        editorForm.setAutoFocus(false);  
        editorForm.setDataSource(supplyItemDS);  
        editorForm.setUseAllDataSourceFields(true);  
  
//        TextItem cid = new TextItem("CID", "CID");
//        
//        TextAreaItem description = new TextAreaItem("description");  
//        description.setWidth(300);  
//        description.setRowSpan(3);  
//  
//        IPickTreeItem category = new IPickTreeItem("category");  
//        category.setWidth(140);  
//        category.setDataSource(supplyCategoryDS);  
//        category.setEmptyMenuMessage("No Sub Categories");  
//        category.setCanSelectParentItems(true);  
  
        ButtonItem saveButton = new ButtonItem("saveItem", "Save Item");  
        saveButton.setAlign(Alignment.CENTER);  
        saveButton.setWidth(100);  
        saveButton.setColSpan(4);  
        saveButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                editorForm.saveData();  
            }  
        });  
  
//        editorForm.setFields(cid, description, category, saveButton);  
        editorForm.setFields(saveButton);  
        editorForm.setColWidths(100, 200, 100, 200);  
  
  
        Tab viewTab = new Tab("View");  
        //viewTab.setIcon("silk/application_form.png");  
        viewTab.setWidth(70);  
        viewTab.setPane(itemViewer);  
  
        Tab editTab = new Tab("Comments");  
        //editTab.setIcon("demoApp/icon_edit.png");  
        editTab.setWidth(70);  
        editTab.setPane(editorForm);  
  
        setTabs(viewTab);  
  
        addTabSelectedHandler(new TabSelectedHandler() {  
            public void onTabSelected(TabSelectedEvent event) {  
                updateDetails();  
            }  
        });  
    }  
  
  
    public void clearDetails(Record selectedCategoryRecord) {  
        int selectedTab = getSelectedTabNumber();  
        if (selectedTab == 0) {  
            //view tab : show empty message  
            itemViewer.setData((Record[]) null);  
        } else {  
            // edit tab : show new record editor, or empty message  
            if (selectedCategoryRecord != null) {  
                updateTab(1, editorForm);  
                Map<String, String> initialValues = new HashMap<String, String>();  
                initialValues.put("category", selectedCategoryRecord.getAttribute("categoryName"));  
                editorForm.editNewRecord(initialValues);  
            } else {  
                updateTab(1, editorLabel);  
            }  
        }  
    }  
  
    public void updateDetails() {  
        Record selectedRecord  = companyTileGrid.getSelectedRecord();  
        int selectedTab = getSelectedTabNumber();  
        if (selectedTab == 0) {  
            //view tab : show empty message  
            itemViewer.setData(new Record[]{selectedRecord});  
        } else {  
            // edit tab : show record editor
        	if (selectedRecord != null)
        		editorForm.editRecord(selectedRecord);  
        }  
    }  
}  

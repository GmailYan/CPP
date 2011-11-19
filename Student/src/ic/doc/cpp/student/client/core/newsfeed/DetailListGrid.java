package ic.doc.cpp.student.client.core.newsfeed;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordExpandEvent;
import com.smartgwt.client.widgets.grid.events.RecordExpandHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DetailListGrid extends ListGrid {
	
	private ListGridRecord lastExpandRecord;
	
	public DetailListGrid() {
		setCanExpandRecords(true);
		setCanExpandMultipleRecords(false);
		addRecordExpandHandler(new RecordExpandHandler() {
			
			@Override
			public void onRecordExpand(RecordExpandEvent event) {
				lastExpandRecord = (ListGridRecord)event.getRecord();
			}
		});
	}
	
	
	
	@Override
    protected Canvas getExpansionComponent(final ListGridRecord record) {
        final ListGrid grid = this;
        VLayout layout = new VLayout(5);
        layout.setPadding(5);

        final DynamicForm df = new DynamicForm();
        df.setNumCols(6);
        
        TextAreaItem text = new TextAreaItem();
        text.setTitle("Description:");
        text.setValue(record.getAttribute("description"));
        
        TextItem from = new TextItem();
        from.setTitle("From");
        from.setValue(record.getAttribute("startDate"));
        

        TextItem to = new TextItem();
        to.setTitle("To");
        to.setValue(record.getAttribute("endDate"));
  
        
        LinkItem website = new LinkItem();
        website.setTitle("Website");
        website.setLinkTitle(record.getAttribute("website"));
        
        df.setFields(text, from, to, website);
        
        IButton likeButton = new IButton("Like");
        likeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	// TODO: add to like event list
            }
        });

        IButton doneButton = new IButton("Done");
        doneButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	grid.collapseRecord(record);
            }
        });
        
        HLayout hLayout = new HLayout(10);
        hLayout.setAlign(Alignment.CENTER);
        hLayout.addMember(likeButton);
        hLayout.addMember(doneButton);

        layout.addMember(df);
        layout.addMember(hLayout);
        return layout;
	}



	public ListGridRecord getLastExpandRecord() {
		return lastExpandRecord;
	}

	public void setLastExpandRecord(ListGridRecord lastExpandRecord) {
		this.lastExpandRecord = lastExpandRecord;
	}

	public void collapseAll() {
		if (lastExpandRecord != null)
			collapseRecord(lastExpandRecord);
	}

}

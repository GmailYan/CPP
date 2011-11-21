package ic.doc.cpp.student.client.core.newsfeed;

import ic.doc.cpp.student.shared.action.AddStudentInterestedEvent;
import ic.doc.cpp.student.shared.action.AddStudentInterestedEventResult;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
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
	
	private final DispatchAsync dispatcher;
	
	public DetailListGrid(final DispatchAsync dispatcher) {
		this.dispatcher = dispatcher;
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
            	dispatcher.execute(new AddStudentInterestedEvent(
            			record.getAttributeAsLong("eventId")),
            			new AsyncCallback<AddStudentInterestedEventResult>() {

					@Override
					public void onFailure(Throwable caught) {
						SC.say("error","Adding interested company fail...");
					}

					@Override
					public void onSuccess(AddStudentInterestedEventResult result) {
						collapseAll();
						record.setAttribute("liked", true);
						refreshFields();
						SC.say("Message", result.getMessage());
					}
				});
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

	public void collapseAll() {
		if (lastExpandRecord != null) {
			collapseRecord(lastExpandRecord);
			lastExpandRecord = null;
		}
	}

}

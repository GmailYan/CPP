package ic.doc.cpp.student.client.core.eventdata;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class EventListGridWidgetView extends ViewImpl implements
		EventListGridWidgetPresenter.MyView {

	private ListGrid widget;
	private HLayout rollOverCanvas;
    private ListGridRecord rollOverRecord;
    private IButton rollOverButton;
	
	@Inject
	public EventListGridWidgetView() {
		GWT.log("init eventListGridWidget()...");
		rollOverButton = new IButton();
		widget = new ListGrid() {
		    @Override
		    protected Canvas getRollOverCanvas(Integer rowNum, Integer colNum) {
		        rollOverRecord = this.getRecord(rowNum);
		        if(rollOverCanvas == null) {
		            rollOverCanvas = new HLayout(3);
		            rollOverCanvas.setSnapTo("TR");
		            rollOverCanvas.setWidth(50);
		            rollOverCanvas.setHeight(22);
		            rollOverButton.setTitle("Like");
		            rollOverButton.setAutoFit(true);
		            rollOverCanvas.addMember(rollOverButton);
		        }
		        
		        return rollOverCanvas;
		    }
		};
		widget.setShowRollOverCanvas(true);
		widget.setCanEdit(false);
		widget.setCanDragRecordsOut(true);
		widget.setHoverWidth(200);
		widget.setHoverHeight(20);
		widget.setSelectionType(SelectionStyle.SINGLE);
	}
	
	@Override
	public HandlerRegistration addRollOverButtonClickHandler(ClickHandler handler) {
		return rollOverButton.addClickHandler(handler);
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
	public Record getRollOverRecord() {
		return rollOverRecord;
	}

	@Override
	public HandlerRegistration addRecordClickHandler(
			RecordClickHandler handler) {
		return widget.addRecordClickHandler(handler);
	}

	@Override
	public void filterData(Criteria criteria) {
		widget.filterData(criteria);
	}

	@Override
	public Record getSelectedRecord() {
		return widget.getSelectedRecord();
	}
}

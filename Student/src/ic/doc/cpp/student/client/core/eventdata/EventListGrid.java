package ic.doc.cpp.student.client.core.eventdata;

import ic.doc.cpp.student.client.core.data.EventDetailXmlDS;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class EventListGrid extends ListGrid{
	private HLayout rollOverCanvas;
    private ListGridRecord rollOverRecord;
    
    public EventListGrid () {
    	 setDataSource(EventDetailXmlDS.getInstance());
         setShowRollOverCanvas(true);
         setCanEdit(false);
         setCanDragRecordsOut(true);
         setHoverWidth(200);
         setHoverHeight(20);
         setSelectionType(SelectionStyle.SINGLE);
    }
    
    @Override
    protected Canvas getRollOverCanvas(Integer rowNum, Integer colNum) {
        rollOverRecord = this.getRecord(rowNum);

        if(rollOverCanvas == null) {
            rollOverCanvas = new HLayout(3);
            rollOverCanvas.setSnapTo("TR");
            rollOverCanvas.setWidth(50);
            rollOverCanvas.setHeight(22);


            IButton likeButton = new IButton();
            likeButton.setTitle("Like");
            likeButton.setAutoFit(true);
            likeButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                	SC.say("You like " + rollOverRecord.getAttribute("title"));
                }
            });

            rollOverCanvas.addMember(likeButton);
        }
        return rollOverCanvas;
    }
}

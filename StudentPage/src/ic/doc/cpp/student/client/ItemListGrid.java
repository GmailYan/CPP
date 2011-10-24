package ic.doc.cpp.student.client;


import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.layout.HLayout;

public class ItemListGrid extends ListGrid {
	
	private HLayout rollOverCanvas;
    private ListGridRecord rollOverRecord;

    public ItemListGrid(DataSource supplyItemDS) {

        setDataSource(supplyItemDS);
        setShowRollOverCanvas(true);
        setAutoFetchData(true);
        
        ListGridField titleFiled = new ListGridField("title", "Title");
        titleFiled.setShowHover(true);
        
        ListGridField companyNameField = new ListGridField("companyName");
        
        ListGridField description = new ListGridField("description");
        description.setShowHover(true);

        ListGridField categoryField = new ListGridField("category");
        categoryField.setCanEdit(false);

        ListGridField websiteField = new ListGridField("website");

        websiteField.setAlign(Alignment.CENTER);
        
        setFields(titleFiled, companyNameField, description, categoryField, websiteField);
        setCanEdit(false);
        setCanDragRecordsOut(true);
        
        // set can expansion details
//        setCanExpandRecords(true);
//        setExpansionMode(ExpansionMode.DETAILS);
        
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


//            ImgButton chartImg = new ImgButton();
//            chartImg.setShowDown(false);
//            chartImg.setShowRollOver(false);
//            chartImg.setLayoutAlign(Alignment.CENTER);
//            chartImg.setSrc("silk/chart_bar.png");
//            chartImg.setPrompt("View Chart");
//            chartImg.setHeight(16);
//            chartImg.setWidth(16);
            IButton likeButton = new IButton();
            likeButton.setTitle("Join");
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

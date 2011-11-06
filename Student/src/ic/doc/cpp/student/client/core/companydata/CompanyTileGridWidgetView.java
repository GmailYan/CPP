package ic.doc.cpp.student.client.core.companydata;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class CompanyTileGridWidgetView extends ViewImpl implements
		CompanyTileGridWidgetPresenter.MyView {

	private TileGrid companyTileGrid;
	
	@Inject
	public CompanyTileGridWidgetView() {
		// initialise TileGrid
		GWT.log("intialise CompanyTileGrid()...");
		companyTileGrid = new TileGrid();
		companyTileGrid.setTileWidth(150);
		companyTileGrid.setTileHeight(205);
		companyTileGrid.setHeight("*");
		companyTileGrid.setCanReorderTiles(true);
		companyTileGrid.setShowAllRecords(true);
		companyTileGrid.setAnimateTileChange(true);
	    
	}
	
	@Override
	public HandlerRegistration addRecordClickHandler(
			RecordClickHandler recordClickHandler) {
		return companyTileGrid.addRecordClickHandler(recordClickHandler);
	}
	
	@Override
	public void setDataSource(DataSource ds) {
		companyTileGrid.setDataSource(ds);
	    
	    DetailViewerField pictureField = new DetailViewerField("logo");
	    //pictureField.setType("image");
	    //pictureField.setImageURLPrefix("companyLogo/");
	    pictureField.setImageSize(140);
	    
	    DetailViewerField nameField = new DetailViewerField("name");
	    DetailViewerField cidField = new DetailViewerField("CID");
	    
	    companyTileGrid.setFields(pictureField, nameField, cidField);
	}

	@Override
	public Widget asWidget() {
		return companyTileGrid;
	}

	@Override
	public HandlerRegistration addShowContextMenuHandler(
			ShowContextMenuHandler showContextMenuHandler) {
		return companyTileGrid.addShowContextMenuHandler(showContextMenuHandler);
	}

	@Override
	public Record getSelectedRecord() {
		return companyTileGrid.getSelectedRecord();
	}

	@Override
	public void filterData(Criteria criteria) {
		companyTileGrid.filterData(criteria);
	}
}

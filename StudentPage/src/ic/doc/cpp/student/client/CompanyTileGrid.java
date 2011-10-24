package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class CompanyTileGrid extends TileGrid {
	
	public CompanyTileGrid(DataSource companyDetailDS) {
		// initialise TileGrid
		GWT.log("intialise CompanyTileGrid()...");
		setTileWidth(150);
	    setTileHeight(205);
	    setHeight(400);
	    setCanReorderTiles(true);
	    setShowAllRecords(true);
	    setDataSource(companyDetailDS);
	    setAutoFetchData(true);
	    setAnimateTileChange(true);
	    
	    DetailViewerField pictureField = new DetailViewerField("logo");
	    //pictureField.setType("image");
	    //pictureField.setImageURLPrefix("companyLogo/");
	    pictureField.setImageSize(140);
	    
	    DetailViewerField nameField = new DetailViewerField("name");
	    setFields(pictureField, nameField);
	    
	}
	
}

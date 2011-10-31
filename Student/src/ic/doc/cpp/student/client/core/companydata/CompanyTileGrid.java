package ic.doc.cpp.student.client.core.companydata;

import ic.doc.cpp.student.client.core.data.CompanyDetailXmlDS;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class CompanyTileGrid extends TileGrid {
	
	public CompanyTileGrid() {
		// initialise TileGrid
		GWT.log("intialise CompanyTileGrid()...");
		setTileWidth(150);
	    setTileHeight(205);
	    setHeight(400);
	    setCanReorderTiles(true);
	    setShowAllRecords(true);
	    setAnimateTileChange(true);
	    
	    setDataSource(CompanyDetailXmlDS.getInstance());
	    
	    DetailViewerField pictureField = new DetailViewerField("logo");
	    //pictureField.setType("image");
	    //pictureField.setImageURLPrefix("companyLogo/");
	    pictureField.setImageSize(140);
	    
	    DetailViewerField nameField = new DetailViewerField("name");
	    DetailViewerField cidField = new DetailViewerField("CID");
	    
	    setFields(pictureField, nameField, cidField);
	}
	
}

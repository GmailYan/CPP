package ic.doc.cpp.student.client.core.profile;

import ic.doc.cpp.student.client.core.data.StudentInterestedCompanyDetailDataSource;
import ic.doc.cpp.student.client.util.CreateRecordFromDto;
import ic.doc.cpp.student.shared.dto.CompanyDto;

import java.util.List;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class InterestedCompanyWidgetView extends ViewImpl implements
		InterestedCompanyWidgetPresenter.MyView {
	
	private final VLayout widget;
	private final TileGrid interestedCompanyTileGrid;
	private final StudentInterestedCompanyDetailDataSource dataSource;
	private DetailViewer detailViewer;
	private final Window winModal;
	
	private Menu interestedCompanyListMenu;  
	
	@Inject
	public InterestedCompanyWidgetView() {
		widget = new VLayout();
		dataSource = StudentInterestedCompanyDetailDataSource.getInstance();

		// set up detail viewer
		setupDetailViewer();
		
		// set up pop up window
		winModal = new Window();
		winModal.setWidth(550);
		winModal.setHeight(550);
		winModal.setTitle("Comapany Viewer");
		winModal.setShowMinimizeButton(false);
		winModal.setIsModal(true);
		winModal.setShowModalMask(true);
		winModal.centerInPage();
		winModal.addItem(detailViewer);
		
		// set up context menu
		setupContextMenu();
		
		// set up tile grid
		interestedCompanyTileGrid = new TileGrid();
		interestedCompanyTileGrid.setTileWidth(150);
		interestedCompanyTileGrid.setTileHeight(205);
		interestedCompanyTileGrid.setAnimateTileChange(true);
		interestedCompanyTileGrid.setAutoFetchData(true);
		interestedCompanyTileGrid.setCanReorderTiles(true);
		interestedCompanyTileGrid.setShowAllRecords(true);
		interestedCompanyTileGrid.setDataSource(dataSource);
		
		DetailViewerField pictureField = new DetailViewerField("logo");
	    pictureField.setImageSize(140);
	    DetailViewerField nameField = new DetailViewerField("name");
	    DetailViewerField cidField = new DetailViewerField("companyId");
	    
	    interestedCompanyTileGrid.setFields(pictureField, nameField, cidField);
	    
		widget.setMembers(interestedCompanyTileGrid);
	}

	private void setupDetailViewer() {
		detailViewer = new DetailViewer();	
		detailViewer.setWidth100();  
		detailViewer.setDataSource(StudentInterestedCompanyDetailDataSource.getInstance());
	}

	private void setupContextMenu() {  
        interestedCompanyListMenu = new Menu();  
        interestedCompanyListMenu.setCellHeight(22);  
        final MenuItem removeMenuItem = new MenuItem("Remove from list");
        interestedCompanyListMenu.setData(removeMenuItem);
	}
	
	@Override
	public void setData(List<CompanyDto> companyDtos) {
		Record[] records = new Record[companyDtos.size()];
		int i = 0;
		for (CompanyDto companyDto : companyDtos) {
			Record record = CreateRecordFromDto.createCompanyTileRecordFromCompanyDto(companyDto);
			records[i++] = record; 
		}
		interestedCompanyTileGrid.setData(records);
	}


	
	@Override
	public HandlerRegistration addRemoveMenuItemClickHandler(ClickHandler handler) {
		return interestedCompanyListMenu.getItem(0).addClickHandler(handler);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public void deleteRecord(Record record) {
		interestedCompanyTileGrid.removeData(record);
	}
	
	@Override
	public void addRecord(Record record) {
		interestedCompanyTileGrid.addData(record);
	}

	@Override
	public Record getSelectedRecord() {
		return interestedCompanyTileGrid.getSelectedRecord();
	}

	@Override
	public HandlerRegistration addShowContextMenuHandler(
			ShowContextMenuHandler showContextMenuHandler) {
		return interestedCompanyTileGrid.addShowContextMenuHandler(showContextMenuHandler);
	}

	@Override
	public Menu getInterestedCompanyListMenu() {
		return interestedCompanyListMenu;
	}
	
	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return interestedCompanyTileGrid.addDoubleClickHandler(handler);
	}

	@Override
	public Canvas getDetailViewer() {
		return detailViewer;
	}

	@Override
	public void setDeatilViewerData(Record record) {
		detailViewer.setData(new Record[] {record});
	}

	@Override
	public HandlerRegistration addWinModalCloseClickHandler(
			CloseClickHandler closeClickHandler) {
		return winModal.addCloseClickHandler(closeClickHandler);
	}

	@Override
	public void hideWinModal() {
		winModal.hide();
	}

	@Override
	public void showWinModal() {
		winModal.show();
	}
}

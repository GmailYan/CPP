package ic.doc.cpp.student.client.core.newsfeed;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;


public class BasePanel extends VLayout {
	
	protected final ListGrid contents;
	protected final BasePanelHeader header;
	protected boolean hasParent;
	
	public BasePanel(boolean hasParent, ListGrid contents) {
		this.hasParent = hasParent;
		this.contents = contents;
		
	  	contents.setWidth100();  
	  	contents.setHeight100();  
	  	contents.setShowAllRecords(true);  
	  	contents.setShowHeader(false);
	  	contents.setSelectionType(SelectionStyle.SINGLE);
	  	contents.setBaseStyle("fullFontsListGridCell");
	  	
	  	ListGridField iconField = new ListGridField("icon", "Icon", 80);  
	  	iconField.setImageSize(80); 
	  	iconField.setAlign(Alignment.RIGHT);
	  	iconField.setType(ListGridFieldType.IMAGE);  
	  	iconField.setCanEdit(false);  
	  	
		ListGridField titleField = new ListGridField("title", "Title"); 
		titleField.setCellFormatter(new CellFormatter() {
			
			@Override
			public String format(Object value, ListGridRecord record, int rowNum,
					int colNum) {
				StringBuilder sb = new StringBuilder();
				sb.append("<span class=\"listViewTitle\" \">");
				sb.append(record.getAttributeAsString("title"));
				sb.append("</span>");
				return sb.toString();
			}
		});
		
		contents.setFields(new ListGridField[] {iconField, titleField});  
		
		header = new BasePanelHeader(hasParent);
		
		addMember(header);
	    addMember(contents);
	}
	
	public BasePanel(String title, boolean hasParent, ListGrid contents) {
		this(hasParent,contents);
		setTitle(title);
	}
	
	public void setTitle(String title) {
	    header.setTitle(title);
	}
	
	public HandlerRegistration addBackHandler(ClickHandler handler) {
		return header.addBackClickHandler(handler);
	}
	
	public void addPanels(PanelLabel[] labels) {
	    contents.setData(labels);
	}

	public HandlerRegistration setEditLabel(String label, String title, ClickHandler handler) {
		return header.setEditLabel(label, title, handler);
	}
	
	public HandlerRegistration addTitleClickHandler(ClickHandler handler) {
		return header.addTitleClickHandler(handler);
	}

	public boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

}

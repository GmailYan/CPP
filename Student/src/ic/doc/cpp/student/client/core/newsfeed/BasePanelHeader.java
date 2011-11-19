package ic.doc.cpp.student.client.core.newsfeed;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class BasePanelHeader extends HLayout {
	private final Label titleLabel;
	private final Label backLabel;
	private final Label editLabel;
	
	public BasePanelHeader(boolean hasParent) {
		setHeight(40);
	    setAlign(VerticalAlignment.CENTER);
		
		titleLabel = new Label();
		backLabel = new Label();
		editLabel = new Label();
		
		if (hasParent) {
			backLabel.setContents("Back");
			backLabel.setWidth("10%");
			backLabel.setAlign(Alignment.CENTER);
			backLabel.setStyleName("backButton");
		} else {
			backLabel.setWidth("10%");
		}
		
		  
	    titleLabel.setWidth100();
	    titleLabel.setAlign(Alignment.CENTER);
	    titleLabel.setStyleName("title");
	    
	    editLabel.setWidth("10%");
	    
	    addMember(backLabel);
	    addMember(titleLabel);
	    addMember(editLabel);
	    
	    setStyleName("header");
	}
	
	public void setTitle(String title) {
		titleLabel.setContents(title);
	}
	
	public HandlerRegistration setEditLabel(String label, String title, ClickHandler handler) {
		editLabel.setContents(label);
		editLabel.setTitle(title);
		editLabel.setAlign(Alignment.CENTER);
		editLabel.setStyleName("goButton");
		return editLabel.addClickHandler(handler);
	}

	public HandlerRegistration addBackClickHandler(ClickHandler handler) {
		return backLabel.addClickHandler(handler);
	}
	
	public HandlerRegistration addTitleClickHandler(ClickHandler handler) {
		return titleLabel.addClickHandler(handler);
	} 
}

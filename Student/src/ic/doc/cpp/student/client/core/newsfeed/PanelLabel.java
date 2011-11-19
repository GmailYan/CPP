package ic.doc.cpp.student.client.core.newsfeed;

import com.google.gwt.user.client.Command;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PanelLabel extends ListGridRecord {
	
	private String originalTitle;
	
	public PanelLabel(String title) {
		setTitle(title);
		this.originalTitle = title;
	}

	public PanelLabel(String icon, String title) {
		this(title);
		setIcon(icon);
	}
	
	public void setTitle(String title) {
		setAttribute("title", title);
	}
	
	public void setIcon(String icon) {
		setAttribute("icon", icon);
	}
	
	public void setClickHandler(Command cmd) {
		setAttribute("command", cmd);
	}

	public Command getCmd() {
		return (Command)getAttributeAsObject("command");
	}

	public void setTitle(int unReadEvents) {
		if (unReadEvents == 0)
			setTitle(originalTitle);
		else
			setTitle(originalTitle + "("+unReadEvents+")");
	}

}

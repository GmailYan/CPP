package ic.doc.cpp.student.client.errorpage;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickHandler;

public class ErrorPageView extends ViewImpl implements
		ErrorPagePresenter.MyView {

	private static String html = "<div>\n"
			+ "<table align=\"center\">\n"
			+ "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
		    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
		    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
		    + "  <tr>\n"    
		    + "    <td style=\"font-weight:bold;\">An error has occurred.</td>\n"
		    + "  </tr>\n"
		    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
		    + "  <tr>\n" + "<td>Try this action again. If the problem continues,</td>\n" + "</tr>\n"
		    + "  <tr>\n" + "<td>contact CPP administrator.</td>\n" + "</tr>\n"
		    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"    
		    + "  <tr>\n"
		    + "    <td id=\"okButtonContainer\"></td>\n"  
		    + "  </tr>\n"     
		    + "</table>\n"
		    + "</div>\n";

	private HTMLPanel panel;

	private final Button okButton;

	public ErrorPageView() {
			    
		panel = new HTMLPanel(html);
	    
	    okButton = new Button("OK");

	    panel.add(okButton, "okButtonContainer");
	    
	}
	
	@Override
	public HandlerRegistration addOkButtonClickHandler(ClickHandler handler) {
		return okButton.addClickHandler(handler);
	}
			  
	@Override
	public Widget asWidget() {
		return panel;
	}
}

package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class Masthead extends HLayout {
	
	private static final int MASTHEAD_HEIGHT = 58;
    
	  public Masthead() {
		super();
			
		GWT.log("init Masthead()...", null);
		
	    // initialise the Masthead layout container
		addStyleName("crm-Masthead");	
	    setHeight(MASTHEAD_HEIGHT);
	    setBackgroundColor("#6960EC");
		// initialise the Logo image
//	    Img logo = new Img("logo.png", 48, 48); 
//	    logo.addStyleName("crm-Masthead-Logo");	  
	    
		// initialise the Name label	
		Label name = new Label();  
		name.addStyleName("crm-MastHead-Name");  
		name.setContents("CPP - Student"); 
	    
	    // initialise the West layout container
	    HLayout westLayout = new HLayout();
	    westLayout.setHeight(MASTHEAD_HEIGHT);	
	    westLayout.setWidth("50%");
//	    westLayout.addMember(logo);
	    westLayout.addMember(name);
	    
	    // initialise the Signed In User label
//		Label signedInUser = new Label();  
//		signedInUser.addStyleName("crm-MastHead-SignedInUser");  
//		signedInUser.setContents("<b>Zhouzhou Du</b>");   
	    
	    // initialise the East layout container
	    HLayout eastLayout = new HLayout();
	    eastLayout.setAlign(Alignment.RIGHT);  
	    eastLayout.setHeight(MASTHEAD_HEIGHT);
	    eastLayout.setWidth("50%");
//	    eastLayout.addMember(signedInUser);
	    
	    // add the West and East layout containers to the Masthead layout container
		this.addMember(westLayout);  	
		this.addMember(eastLayout); 
	  }	
}

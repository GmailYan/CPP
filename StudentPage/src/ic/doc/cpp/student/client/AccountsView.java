package ic.doc.cpp.student.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;

public class AccountsView extends VLayout {
	
  private static final String DESCRIPTION = "AccountsView";
	
  public AccountsView() {
	super();
		
	GWT.log("init AccountsView()...", null);
		
	// initialise the Accounts View layout container
    this.addStyleName("crm-ContextArea");
    this.setWidth("*"); 
    
    // add the List Grid to the Accounts View layout container
    this.addMember(new ContextAreaListGrid());
  }

  public static class Factory implements ContextAreaFactory {
		
    private String id;
    public static Canvas view;
	  
    public Canvas create() {
    	if (view == null)
    		view = new AccountsView();
      id = view.getID();
	      
      GWT.log("AccountsView.Factory.create()->view.getID() - " + id, null);
      return view;
    }

    public String getID() {
      return id;
    }

    public String getDescription() {
      return DESCRIPTION;
    }
  }	  
}
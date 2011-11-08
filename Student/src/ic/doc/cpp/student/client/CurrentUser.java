package ic.doc.cpp.student.client;


public class CurrentUser {
	
	private String login;
	  
	private boolean loggedIn = true;
	private boolean administrator;
  
	public CurrentUser(String login) {
		this.login = login;
	}
	  
	public String getLogin() {
		return login;    
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
}

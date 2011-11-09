package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import ic.doc.cpp.student.shared.action.LoginResult;
import java.lang.String;

public class Login extends UnsecuredActionImpl<LoginResult> {

	private String login;
	private String password;

	@SuppressWarnings("unused")
	private Login() {
		// For serialization only
	}

	public Login(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
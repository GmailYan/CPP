package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;
import java.lang.String;

public class LoginResult implements Result {

	private String sessionKey;

	@SuppressWarnings("unused")
	private LoginResult() {
		// For serialization only
	}

	public LoginResult(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionKey() {
		return sessionKey;
	}
}

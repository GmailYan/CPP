package ic.doc.cpp.student.shared;

public class FieldVerifier {

	public static boolean isValidUserName(String userName) {
		return true;
	}

	public static boolean isValidPassword(String password) {
		return password.length() >= 6;
	}

}

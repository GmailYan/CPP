package ic.doc.cpp.student.shared;

public class FieldVerifier {
	
	public static boolean isValidUserName(String name) {
		return (name != null);
	}

	/*

	  (                         # Start of group
	      (?=.*\d)              #   must contains one digit from 0-9
	      (?=.*[a-z])           #   must contains one lower case characters
	      (?=.*[A-Z])           #   must contains one upper case characters
	      (?=.*[@#$%])          #   must contains one special symbols in the list "@#$%"
	                  .         #   match anything with previous condition checking
	      {8,32}                #   length at least 8 characters and maximum of 32
	    )                       # End of group

	    Passwords must contain at least 8 characters with at least one digit,
	    one upper case letter, one lower case letter and one special symbol (@#$%).

	  */
		   
 	private static final String PASSWORD_VALIDATION_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,32})";

 	public static boolean isValidPassword(String password) {

 		if (password == null) {
 			return false;
 		}

 		return password.matches(PASSWORD_VALIDATION_REGEX);
  }

}

package ic.doc.cpp.student.client;

public class AccountData {

  private static AccountRecord[] records;

  public static AccountRecord[] getRecords() {
	if (records == null) {
	  records = getNewRecords();
	}
	return records;
  }

  public static AccountRecord[] getNewRecords() {
	return new AccountRecord[]{
	  new AccountRecord("ms", "Morgan Stanley", "Investment Banking", "Canary Wharff", 
			  			"www.morganstanley.com", "hr@morganstanley.com"),
	  new AccountRecord("ms", "Morgan Stanley", "Investment Banking", "Canary Wharff", 
	  					"www.morganstanley.com", "hr@morganstanley.com"),
      new AccountRecord("ms", "Morgan Stanley", "Investment Banking", "Canary Wharff", 
						"www.morganstanley.com", "hr@morganstanley.com"),
			  			
	};
  }
}
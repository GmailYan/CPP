package ic.doc.cpp.student.client.core.data;

public class CompanyEventsDetailDataSource extends EventDetailDataSource {
	
	private static CompanyEventsDetailDataSource instance = null;  
	  
	public static CompanyEventsDetailDataSource getInstance() {  
	    if (instance == null) {  
	        instance = new CompanyEventsDetailDataSource("companyEventDetails");  
	    }  
	    return instance;  
	}

	public CompanyEventsDetailDataSource(String id) {
		super(id);
	}

}

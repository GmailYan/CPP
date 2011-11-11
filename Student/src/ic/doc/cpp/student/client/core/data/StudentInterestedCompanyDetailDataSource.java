package ic.doc.cpp.student.client.core.data;

public class StudentInterestedCompanyDetailDataSource extends
		CompanyDetailDataSource {
	
	private static StudentInterestedCompanyDetailDataSource instance = null;  
	  
	public static StudentInterestedCompanyDetailDataSource getInstance() {  
	    if (instance == null)
	        instance = new StudentInterestedCompanyDetailDataSource("StudentInterestedCompanyDetails");  

	    return instance;  
	}  

	public StudentInterestedCompanyDetailDataSource(String id) {
		super(id);
	}

}

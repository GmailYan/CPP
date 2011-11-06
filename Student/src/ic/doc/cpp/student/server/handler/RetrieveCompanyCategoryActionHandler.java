package ic.doc.cpp.student.server.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.CompanyCategoryDao;
import ic.doc.cpp.student.server.domain.CompanyCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategory;
import ic.doc.cpp.student.shared.action.RetrieveCompanyCategoryResult;
import ic.doc.cpp.student.shared.dto.CompanyCategoryDto;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanyCategoryActionHandler implements
		ActionHandler<RetrieveCompanyCategory, RetrieveCompanyCategoryResult> {

	
	@Inject
	public RetrieveCompanyCategoryActionHandler() {
	}
	
   public RetrieveCompanyCategoryResult get()
    {
        String loginUser = "postgres";
        String loginPasswd = "dzziqbaby";
        String loginUrl = "jdbc:postgresql://localhost/postgres";
        Connection dbcon = null;
        
        // Load the PostgreSQL driver
        try 
        {
        	Class.forName("org.postgresql.Driver");
        	dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("ClassNotFoundException: " + ex.getMessage());
            return null;
        } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
        
        try
        {
	        // Declare our statement
	        Statement statement = dbcon.createStatement();
	
	        String query = "SELECT * FROM company_category";
	
	        // Perform the query
	        ResultSet rs = statement.executeQuery(query);
	        List<CompanyCategoryDto> companyCategoryDtos = new ArrayList<CompanyCategoryDto>();
	        // Iterate through each row of rs
	        while (rs.next())
	        {
	           Long id = rs.getLong("category_id");
	           String name = rs.getString("category_name");
	           Long p_id = rs.getLong("parent_id");
	           companyCategoryDtos.add(new CompanyCategoryDto(id, name, p_id));
	        }
	
	        return new RetrieveCompanyCategoryResult(companyCategoryDtos);
        }
        catch(Exception ex)
        {	
            return null;
        }
        
    }

	@Override
	public RetrieveCompanyCategoryResult execute(RetrieveCompanyCategory action,
			ExecutionContext context) throws ActionException {
		RetrieveCompanyCategoryResult result = null;
		
		CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
		
		try {
			List<CompanyCategory> companyCategorys = companyCategoryDao.retrieveCompanyCategorys();
			
			if (companyCategorys != null) {
				List<CompanyCategoryDto> companyCategoryDtos = new ArrayList<CompanyCategoryDto>(companyCategorys.size());
				
				for (CompanyCategory category : companyCategorys) {
					companyCategoryDtos.add(createCompanyCategoryDto(category));
				}
				result = new RetrieveCompanyCategoryResult(companyCategoryDtos);
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	private CompanyCategoryDto createCompanyCategoryDto(CompanyCategory category) {
		return new CompanyCategoryDto(category.getCategoryId(), 
				category.getCategoryName(), category.getParentId());
	}

	@Override
	public void undo(RetrieveCompanyCategory action,
			RetrieveCompanyCategoryResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveCompanyCategory> getActionType() {
		return RetrieveCompanyCategory.class;
	}
}

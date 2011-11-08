package ic.doc.cpp.student.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CompanyCategoryTest.class, 
		EventCategoryTest.class, CompanyTest.class, EventTest.class, StudentUserTest.class })
public class AllTests {

}

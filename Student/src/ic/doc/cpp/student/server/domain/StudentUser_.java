package ic.doc.cpp.student.server.domain;

import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.CompanyCategory;
import ic.doc.cpp.student.server.domain.Event;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.1.v20111018-r10243", date="2011-11-18T12:01:31")
@StaticMetamodel(StudentUser.class)
public class StudentUser_ { 

    public static volatile SingularAttribute<StudentUser, String> lastName;
    public static volatile SingularAttribute<StudentUser, String> email;
    public static volatile ListAttribute<StudentUser, Company> companys;
    public static volatile ListAttribute<StudentUser, Event> events;
    public static volatile SingularAttribute<StudentUser, String> gender;
    public static volatile ListAttribute<StudentUser, CompanyCategory> interestedArea;
    public static volatile SingularAttribute<StudentUser, String> login;
    public static volatile SingularAttribute<StudentUser, String> firstName;
    public static volatile SingularAttribute<StudentUser, String> password;
    public static volatile SingularAttribute<StudentUser, String> salt;

}
package ic.doc.cpp.student.server.domain;

import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.EventCategory;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.1.v20111018-r10243", date="2011-11-18T12:01:31")
@StaticMetamodel(Event.class)
public class Event_ extends BaseEntity_ {

    public static volatile SingularAttribute<Event, String> picture;
    public static volatile SingularAttribute<Event, EventCategory> category;
    public static volatile SingularAttribute<Event, Date> end_date;
    public static volatile SingularAttribute<Event, String> title;
    public static volatile SingularAttribute<Event, Long> eventId;
    public static volatile SingularAttribute<Event, String> website;
    public static volatile SingularAttribute<Event, String> description;
    public static volatile SingularAttribute<Event, Company> company;
    public static volatile SingularAttribute<Event, Date> start_date;

}
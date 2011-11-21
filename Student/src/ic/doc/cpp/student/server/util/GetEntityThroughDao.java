package ic.doc.cpp.student.server.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.inject.Provider;

import ic.doc.cpp.student.server.dao.CompanyDao;
import ic.doc.cpp.student.server.dao.EventDao;
import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.Company;
import ic.doc.cpp.student.server.domain.Event;
import ic.doc.cpp.student.server.domain.StudentUser;

public class GetEntityThroughDao {
	public static StudentUser getStudentUser(final Provider<HttpServletRequest> provider) {
		HttpSession session = provider.get().getSession();
		String login = session.getAttribute("login.authenticated").toString();
		StudentUserDao studentDao = new StudentUserDao();
		return studentDao.retrieveUser(login);
	}
	
	public static Company getCompany(Long companyId) {
		CompanyDao companyDao = new CompanyDao();
		return companyDao.retrieveCompany(companyId);
	}
	
	public static Event getEvent(Long eventId) {
		EventDao eventDao = new EventDao();
		return eventDao.retrieveEvent(eventId);
	}
}

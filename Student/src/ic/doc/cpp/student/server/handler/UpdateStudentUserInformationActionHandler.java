package ic.doc.cpp.student.server.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.CreateFromDto;
import ic.doc.cpp.student.shared.action.UpdateStudentUserInformation;
import ic.doc.cpp.student.shared.action.UpdateStudentUserInformationResult;
import ic.doc.cpp.student.shared.dto.StudentUserDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdateStudentUserInformationActionHandler
		implements
		ActionHandler<UpdateStudentUserInformation, UpdateStudentUserInformationResult> {

	private final Provider<HttpServletRequest> requestProvider;
	
	@Inject
	public UpdateStudentUserInformationActionHandler(final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public UpdateStudentUserInformationResult execute(
			UpdateStudentUserInformation action, ExecutionContext context)
			throws ActionException {
		UpdateStudentUserInformationResult result = null;
		
		try {
			HttpSession session = requestProvider.get().getSession();
			String login = session.getAttribute("login.authenticated").toString();
			StudentUserDao studentUserDao = new StudentUserDao();
			StudentUser user = studentUserDao.retrieveUser(login);
			if (user != null)
				studentUserDao.updateUser(updateStudentUser(user, action.getStudentUserDto()));
		} catch(Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	private StudentUser updateStudentUser(StudentUser user, StudentUserDto studentUserDto) {
		if (studentUserDto.getCompanyDtos() != null)
			user.setCompanys(CreateFromDto.getCompanysFromDtos(studentUserDto.getCompanyDtos()));
		if (studentUserDto.getEmail() != null)
			user.setEmail(studentUserDto.getEmail());
		if (studentUserDto.getEventDtos() != null)
			user.setEvents(CreateFromDto.getEventsFromDtos(studentUserDto.getEventDtos()));
		if (studentUserDto.getFirstName() != null)
			user.setFirstName(studentUserDto.getFirstName());
		if (studentUserDto.getGender() != null)
			user.setGender(studentUserDto.getGender());
		if (studentUserDto.getInterestedAreaDtos() != null)
			user.setInterestedArea(CreateFromDto.getCompanyCategoryFromDto(studentUserDto.getInterestedAreaDtos()));
		if (studentUserDto.getLastName() != null)
			user.setLastName(studentUserDto.getLastName());
		if (studentUserDto.getPassword() != null)
			user.setPassword(studentUserDto.getPassword());
		if (studentUserDto.getSalt() != null)
			user.setSalt(studentUserDto.getSalt());
		return user;
	}
	
	@Override
	public void undo(UpdateStudentUserInformation action,
			UpdateStudentUserInformationResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<UpdateStudentUserInformation> getActionType() {
		return UpdateStudentUserInformation.class;
	}
}

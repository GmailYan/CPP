package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.UpdateStudentUserInformationResult;
import ic.doc.cpp.student.shared.dto.StudentUserDto;

public class UpdateStudentUserInformation extends
		ActionImpl<UpdateStudentUserInformationResult> {

	private StudentUserDto student;

	@SuppressWarnings("unused")
	private UpdateStudentUserInformation() {
		// For serialization only
	}

	public UpdateStudentUserInformation(StudentUserDto student) {
		this.student = student;
	}

	public StudentUserDto getStudentUserDto() {
		return student;
	}

	@Override
	public boolean isSecured() {
		return true;
	}
	
	
}

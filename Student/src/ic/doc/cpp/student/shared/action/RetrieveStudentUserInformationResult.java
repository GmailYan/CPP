package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;
import ic.doc.cpp.student.shared.dto.StudentUserDto;

public class RetrieveStudentUserInformationResult implements Result {

	private StudentUserDto studentUserDto;

	@SuppressWarnings("unused")
	private RetrieveStudentUserInformationResult() {
		// For serialization only
	}

	public RetrieveStudentUserInformationResult(StudentUserDto studentUserDto) {
		this.studentUserDto = studentUserDto;
	}

	public StudentUserDto getStudentUserDto() {
		return studentUserDto;
	}
}

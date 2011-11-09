package ic.doc.cpp.student.shared.dto;

import java.io.Serializable;
import java.util.List;

public class StudentUserDto implements Serializable {

	private static final long serialVersionUID = 6892336204461276891L;

	protected String login;
	protected String salt;
	protected String password;
	protected String email;
	protected String firstName;
	protected String lastName;
	protected String gender;
	protected List<EventDto> eventDtos;
	protected List<CompanyDto> companyDtos;
	protected List<CompanyCategoryDto> interestedAreaDtos;
	
	public StudentUserDto() {}

	public StudentUserDto(String login, String salt, String password,
			String email, String firstName, String lastName, String gender,
			List<EventDto> eventDtos, List<CompanyDto> companyDtos,
			List<CompanyCategoryDto> interestedAreaDtos) {
		super();
		this.login = login;
		this.salt = salt;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.eventDtos = eventDtos;
		this.companyDtos = companyDtos;
		this.interestedAreaDtos = interestedAreaDtos;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<EventDto> getEventDtos() {
		return eventDtos;
	}

	public void setEventDtos(List<EventDto> eventsDtos) {
		this.eventDtos = eventsDtos;
	}

	public List<CompanyCategoryDto> getInterestedAreaDtos() {
		return interestedAreaDtos;
	}

	public void setInterestedAreaDtos(List<CompanyCategoryDto> interestedAreaDtos) {
		this.interestedAreaDtos = interestedAreaDtos;
	}

	public List<CompanyDto> getCompanyDtos() {
		return companyDtos;
	}


	public void setCompanyDtos(List<CompanyDto> companyDtos) {
		this.companyDtos = companyDtos;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [login=");
	    builder.append(login);
	    builder.append(", salt=");
	    builder.append(salt);
	    builder.append(", password=");
	    builder.append(password);
	    builder.append("]");
	    return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    result = prime * result + ((login == null) ? 0 : login.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentUserDto))
			return false;
		StudentUserDto other = (StudentUserDto) obj;
		if (login == null) {
			if (other.login != null)
				return false;
	    } else if (!login.equals(other.login))
	    	return false;
	    if (password == null) {
	    	if (other.password != null)
	    		return false;
	    } else if (!password.equals(other.password))
	    	return false;
	    if (salt == null) {
	    	if (other.salt != null)
	    		return false;
	    } else if (!salt.equals(other.salt))
	    	return false;
	    return true;
	}

}

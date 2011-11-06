package ic.doc.cpp.student.server.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student_user")
public class StudentUser {
	@Id
	@Column(name = "login", length = 32)
	protected String login;

	@Column(name = "salt", length = 32)
	protected String salt;

	@Column(name = "password", length = 32)
	protected String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "student_user_companys",
		joinColumns = {@JoinColumn(name = "login")},
		inverseJoinColumns = {@JoinColumn(name = "company_id")})
	protected List<Company> companys;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "student_user_events",
		joinColumns = {@JoinColumn(name = "login")},
		inverseJoinColumns = {@JoinColumn(name = "event_id")})
	protected List<Event> events;
	
	
	public StudentUser() {}
	
	public StudentUser(String login, String salt, String password) {
		this.login = login;
		this.salt = salt;
		this.password = password;
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

	public List<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Company> companys) {
		this.companys = companys;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
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
	
//	public String getInterestedCompany() {
//
//		if (companys == null || (companys.size() == 0)) {
//			return "No Address details";
//		}
//
//	    StringBuilder sb = new StringBuilder();
//	    String companysElement;
//
//	    Iterator<Company> it = companys.iterator();
//	    Company company = it.next();
//
//	    companysElement = company.getAddressLine1();
//	    sb.append(companysElement).append(", ");
//	    companysElement = company.getAddressLine2();
//	    sb.append(companysElement).append(" ");
//	    companysElement = company.getCity();
//	    sb.append(companysElement).append(" ");
//	    companysElement = company.getState();
//	    sb.append(companysElement).append(" ");
//	    companysElement = company.getPostalCode();
//	    sb.append(companysElement);
//
//	    // e.g. "Level 111, 111 Kent Street Sydney NSW 2000"
//	    return sb.toString();
//	  }
	
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
		if (!(obj instanceof StudentUser))
			return false;
		StudentUser other = (StudentUser) obj;
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

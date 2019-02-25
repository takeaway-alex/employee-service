package takeaway.entities.dto;

import java.util.UUID;

public class EmployeeDto {
	private UUID id;
	private String email;
	private String fullName;
	private String birthday;
	private Long departmentId;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(UUID id, String email, String fullName, String birthday, Long departmentId) {
		super();
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.birthday = birthday;
		this.departmentId = departmentId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}

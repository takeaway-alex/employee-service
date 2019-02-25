package takeaway.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import takeaway.entities.dto.EmployeeDto;

@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	private String email;
	private String fullName;
	private LocalDate birthday;
	@ManyToOne
	private Department department;

	public Employee() {
		super();
	}

	public Employee(String email, String fullName, LocalDate birthday, Department department) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.birthday = birthday;
		this.department = department;
	}

	public Employee(EmployeeDto employeeDto) {
		super();
		this.email = employeeDto.getEmail();
		this.fullName = employeeDto.getFullName();
		this.birthday = LocalDate.parse(employeeDto.getBirthday());
		this.department = new Department(employeeDto.getDepartmentId(), null);
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = LocalDate.parse(birthday);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public EmployeeDto getEmployeeDto() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String birthday = this.getBirthday().format(formatter);
		return new EmployeeDto(this.getId(), this.getEmail(), this.getFullName(), birthday,
				this.getDepartment().getId());
	}
}
package takeaway.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import takeaway.entities.dto.DepartmentDto;

@Entity
@Table(name = "department", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	public Department() {
		super();
	}

	public Department(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Department(DepartmentDto department) {
		super();
		this.name = department.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public DepartmentDto getDepartmentDto() {
		return new DepartmentDto(id, name);
	}
}
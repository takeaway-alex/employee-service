package takeaway.entities.dto;

public class DepartmentDto {
	private Long id;

	private String name;

	public DepartmentDto() {
		super();
	}

	public DepartmentDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

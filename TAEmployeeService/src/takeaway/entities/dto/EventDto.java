package takeaway.entities.dto;

public class EventDto {
	private String action;
	private Long timestamp;
	private EmployeeDto employee;

	public EventDto() {
		super();
	}

	public EventDto(String action, EmployeeDto employee) {
		super();
		this.action = action;
		this.employee = employee;
		this.timestamp = System.currentTimeMillis();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}
}

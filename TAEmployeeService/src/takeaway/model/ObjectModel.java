package takeaway.model;

import java.util.List;
import java.util.UUID;

import takeaway.entities.dto.DepartmentDto;
import takeaway.entities.dto.EmployeeDto;

public interface ObjectModel {
	List<DepartmentDto> getDepartments();

	Long createDepartment(DepartmentDto department);

	List<EmployeeDto> getEmployees();

	EmployeeDto getEmployee(UUID uuid);

	UUID createEmployee(EmployeeDto employee);

	void updateEmployee(EmployeeDto employee);

	void deleteEmployee(UUID uuid);
}

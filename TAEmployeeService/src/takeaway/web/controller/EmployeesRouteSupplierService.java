package takeaway.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import takeaway.entities.dto.DepartmentDto;
import takeaway.entities.dto.EmployeeDto;
import takeaway.model.ObjectModel;

@SpringBootApplication
@RestController
@ImportResource({ "classpath:beans.xml" })
@ComponentScan({ "takeaway" })
public class EmployeesRouteSupplierService {

	@Autowired
	ObjectModel objectModel;

	@CrossOrigin
	@RequestMapping(value = "departments", method = RequestMethod.GET)
	List<DepartmentDto> getDepartments() {
		return objectModel.getDepartments();
	}

	@CrossOrigin
	@RequestMapping(value = "department", method = RequestMethod.POST)
	Long createDepartment(@RequestBody DepartmentDto department) throws JsonProcessingException {
		return objectModel.createDepartment(department);
	}

	@CrossOrigin
	@RequestMapping(value = "employees", method = RequestMethod.GET)
	List<EmployeeDto> getEmployees() {
		return objectModel.getEmployees();
	}

	@CrossOrigin
	@RequestMapping(value = "employee", method = RequestMethod.GET)
	EmployeeDto getEmployee(@RequestParam UUID uuid) {
		return objectModel.getEmployee(uuid);
	}

	@CrossOrigin
	@RequestMapping(value = "employee", method = RequestMethod.POST)
	UUID createEmployee(@RequestBody EmployeeDto employeeDto) throws JsonProcessingException {
		return objectModel.createEmployee(employeeDto);
	}

	@CrossOrigin
	@RequestMapping(value = "employee", method = RequestMethod.PUT)
	void updateDepartment(@RequestBody EmployeeDto employeeDto) throws JsonProcessingException {
		objectModel.updateEmployee(employeeDto);
	}

	@CrossOrigin
	@RequestMapping(value = "employee", method = RequestMethod.DELETE)
	void deleteDepartment(@RequestParam UUID uuid) throws JsonProcessingException {
		objectModel.deleteEmployee(uuid);
	}
}
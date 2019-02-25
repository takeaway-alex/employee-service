package takeaway.model;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import takeaway.entities.Department;
import takeaway.entities.Employee;
import takeaway.entities.dto.DepartmentDto;
import takeaway.entities.dto.EmployeeDto;
import takeaway.entities.dto.EventDto;
import takeaway.helpers.RabbitHelper;

@Component
public class ObjectORM implements ObjectModel {
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;

	@Autowired
	RabbitHelper rabbit;

	@Override
	public List<DepartmentDto> getDepartments() {
		List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
		List<DepartmentDto> departmentsDto = departments.stream().map(department -> department.getDepartmentDto())
				.collect(Collectors.toList());
		return departmentsDto;
	}

	@Override
	@Transactional
	public Long createDepartment(DepartmentDto department) {
		if (department.getId() != null && em.find(Department.class, department.getId()) != null)
			return null;
		Department d = new Department(department);
		em.persist(d);
		return d.getId();
	}

	@Override
	public List<EmployeeDto> getEmployees() {
		List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		List<EmployeeDto> employeesDto = employees.stream().map(employee -> employee.getEmployeeDto())
				.collect(Collectors.toList());
		return employeesDto;
	}

	@Override
	public EmployeeDto getEmployee(UUID uuid) {
		Employee employee = em.find(Employee.class, uuid);
		EmployeeDto employeeDto = employee.getEmployeeDto();
		return employeeDto;
	}

	@Override
	@Transactional
	public UUID createEmployee(EmployeeDto employeeDto) {
		Employee e = new Employee(employeeDto);
		em.persist(e);
		rabbit.send(new EventDto("create", employeeDto));
		return e.getId();
	}

	@Override
	@Transactional
	public void updateEmployee(EmployeeDto employeeDto) {
		Employee employee = em.find(Employee.class, employeeDto.getId());
		if (employee != null) {
			Long departmentId = employeeDto.getDepartmentId();
			Department department = em.find(Department.class, departmentId);
			employee.setDepartment(department);
			employee.setBirthday(employeeDto.getBirthday());
			employee.setEmail(employeeDto.getEmail());
			employee.setFullName(employeeDto.getFullName());
			EventDto dto = new EventDto("update", employeeDto);
			rabbit.send(dto);
		}
	}

	@Override
	@Transactional
	public void deleteEmployee(UUID uuid) {
		Employee employee = em.find(Employee.class, uuid);
		if (employee != null) {
			em.remove(employee);
			rabbit.send(new EventDto("delete", null));
		}
	}
}

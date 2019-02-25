package test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import takeaway.entities.Department;
import takeaway.entities.Employee;
import takeaway.entities.dto.DepartmentDto;
import takeaway.entities.dto.EmployeeDto;
import takeaway.model.ObjectModel;
import takeaway.web.controller.EmployeesAppl;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = EmployeesAppl.class)
@TestPropertySource(locations = "classpath:application.test.properties")
@ComponentScan({ "takeaway" })
public class GeneralUnitTest {
	@PersistenceContext(unitName = "springHibernate")
	private EntityManager em;

	@Autowired
	ObjectModel objectModel;

	@Test
	public void testGetDepartments() {
		String name = "R&D";
		Department department = new Department(null, name);
		em.persist(department);
		em.flush();
		List<DepartmentDto> departments = objectModel.getDepartments();

		assertThat(departments.size()).isEqualTo(1);
		assertThat(departments.get(0).getName()).isEqualTo(name);

	}

	@Test
	public void testCreateDepartment() {
		String name = "Sales";
		DepartmentDto department = new DepartmentDto(null, name);
		objectModel.createDepartment(department);
		List<DepartmentDto> departments = objectModel.getDepartments();
		assertThat(departments.size()).isEqualTo(1);
		assertThat(departments.get(0).getName()).isEqualTo(name);
	}

	@Test
	public void testGetEmployees() {
		String name = "R&D", email = "john@gmail.com", date = "1999-05-12";
		Department department = new Department(null, name);
		em.persist(department);
		Employee employee = new Employee(email, "John Doe", LocalDate.parse(date), department);
		em.persist(employee);
		em.flush();
		List<EmployeeDto> employees = objectModel.getEmployees();

		assertThat(employees.size()).isEqualTo(1);
		EmployeeDto e = employees.get(0);
		assertThat(e.getEmail()).isEqualTo(email);
		assertThat(e.getBirthday()).isEqualTo(date);
		assertThat(e.getDepartmentId()).isEqualTo(department.getId());
	}

	@Test
	public void testCreateEmployee() {
		String name = "Management", email = "peter@gmail.com", date = "1930-02-15";
		Department department = new Department(null, name);
		em.persist(department);
		em.flush();
		EmployeeDto employee = new EmployeeDto(null, email, "Peter Pan", date, department.getId());
		objectModel.createEmployee(employee);

		List<EmployeeDto> employees = objectModel.getEmployees();

		assertThat(employees.size()).isEqualTo(1);
		EmployeeDto e = employees.get(0);
		assertThat(e.getEmail()).isEqualTo(email);
		assertThat(e.getBirthday()).isEqualTo(date);
		assertThat(e.getDepartmentId()).isEqualTo(department.getId());
	}

	@Test
	public void testUpdateEmployee() {
		String name = "R2&D2", email = "r2d2@gmail.com", date = "1981-05-12";
		Department department = new Department(null, name);
		em.persist(department);
		Employee employee = new Employee(email, "r2d2", LocalDate.parse(date), department);
		em.persist(employee);
		em.flush();

		UUID id = employee.getId();
		String updName = "r3d3", updEmail = "r3d3@gmail.com", updDate = "1991-05-12";
		EmployeeDto updEmployee = new EmployeeDto(id, updEmail, updName, updDate, department.getId());
		objectModel.updateEmployee(updEmployee);

		List<EmployeeDto> employees = objectModel.getEmployees();

		assertThat(employees.size()).isEqualTo(1);
		EmployeeDto e = employees.get(0);
		assertThat(e.getEmail()).isEqualTo(updEmail);
		assertThat(e.getFullName()).isEqualTo(updName);
		assertThat(e.getBirthday()).isEqualTo(updDate);
	}

	@Test
	public void testDeleteEmployee() {
		String name = "R2&D2", email = "r2d2@gmail.com", date = "1981-05-12";
		Department department = new Department(null, name);
		em.persist(department);
		Employee employee = new Employee(email, "r2d2", LocalDate.parse(date), department);
		em.persist(employee);
		em.flush();

		UUID id = employee.getId();
		objectModel.deleteEmployee(id);

		List<EmployeeDto> employees = objectModel.getEmployees();

		assertThat(employees.size()).isEqualTo(0);
	}

}

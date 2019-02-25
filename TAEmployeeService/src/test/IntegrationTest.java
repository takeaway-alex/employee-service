package test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import takeaway.entities.dto.DepartmentDto;
import takeaway.entities.dto.EmployeeDto;
import takeaway.web.controller.EmployeesAppl;
import takeaway.web.controller.EmployeesRouteSupplierService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EmployeesAppl.class)
@TestPropertySource(locations = "classpath:application.test.properties")
@ComponentScan({ "takeaway" })
@WebMvcTest(EmployeesRouteSupplierService.class)
public class IntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void complexIntegrationTest() {

		String departmentName = "R&D";
		try {
			mvc.perform(post("/department").content("{\"name\":\"" + departmentName + "\"}")
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			String depJson = mvc.perform(get("/departments").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
					.andExpect(jsonPath("$[0].name", equalTo(departmentName))).andReturn().getResponse()
					.getContentAsString();

			DepartmentDto department = new ObjectMapper().readValue(depJson, DepartmentDto[].class)[0];

			String fullName = "Alex Brown";
			mvc.perform(post("/employee")
					.content("{\"email\":\"a@cd.com\",\"fullName\":\"" + fullName
							+ "\", \"birthday\":\"1933-01-12\",\"departmentId\":" + department.getId() + "}")
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

			String empJson = mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andReturn().getResponse()
					.getContentAsString();
			EmployeeDto employee = new ObjectMapper().readValue(empJson, EmployeeDto[].class)[0];

			String updFullName = "John Doe";
			mvc.perform(put("/employee")
					.content(
							"{\"id\":\"" + employee.getId() + "\",\"email\":\"ab@cd.com\",\"fullName\":\"" + updFullName
									+ "\", \"birthday\":\"1933-01-12\",\"departmentId\":" + department.getId() + "}")
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

			empJson = mvc.perform(get("/employee?uuid=" + employee.getId()).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			employee = new ObjectMapper().readValue(empJson, EmployeeDto.class);
			assertThat(employee.getFullName()).isEqualTo(updFullName);

			mvc.perform(delete("/employee?uuid=" + employee.getId()).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			empJson = mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("$").isArray()).andReturn().getResponse().getContentAsString();
			EmployeeDto[] employees = new ObjectMapper().readValue(empJson, EmployeeDto[].class);
			assertThat(employees.length).isZero();

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	// @Test
	// public void testCreateDepartment() {
	// String name = "Sales";
	// DepartmentDto department = new DepartmentDto(null, name);
	// objectModel.createDepartment(department);
	// List<DepartmentDto> departments = objectModel.getDepartments();
	// assertThat(departments.size()).isEqualTo(1);
	// assertThat(departments.get(0).getName()).isEqualTo(name);
	// }
	//
	// @Test
	// public void testGetEmployees() {
	// String name = "R&D", email = "john@gmail.com", date = "1999-05-12";
	// Department department = new Department(null, name);
	// em.persist(department);
	// Employee employee = new Employee(email, "John Doe",
	// LocalDate.parse(date), department);
	// em.persist(employee);
	// em.flush();
	// List<EmployeeDto> employees = objectModel.getEmployees();
	//
	// assertThat(employees.size()).isEqualTo(1);
	// EmployeeDto e = employees.get(0);
	// assertThat(e.getEmail()).isEqualTo(email);
	// assertThat(e.getBirthday()).isEqualTo(date);
	// assertThat(e.getDepartmentId()).isEqualTo(department.getId());
	// }
	//
	// @Test
	// public void testCreateEmployee() {
	// String name = "Management", email = "peter@gmail.com", date =
	// "1930-02-15";
	// Department department = new Department(null, name);
	// em.persist(department);
	// em.flush();
	// EmployeeDto employee = new EmployeeDto(null, email, "Peter Pan", date,
	// department.getId());
	// objectModel.createEmployee(employee);
	//
	// List<EmployeeDto> employees = objectModel.getEmployees();
	//
	// assertThat(employees.size()).isEqualTo(1);
	// EmployeeDto e = employees.get(0);
	// assertThat(e.getEmail()).isEqualTo(email);
	// assertThat(e.getBirthday()).isEqualTo(date);
	// assertThat(e.getDepartmentId()).isEqualTo(department.getId());
	// }
	//
	// @Test
	// public void testUpdateEmployee() {
	// String name = "R2&D2", email = "r2d2@gmail.com", date = "1981-05-12";
	// Department department = new Department(null, name);
	// em.persist(department);
	// Employee employee = new Employee(email, "r2d2", LocalDate.parse(date),
	// department);
	// em.persist(employee);
	// em.flush();
	//
	// UUID id = employee.getId();
	// String updName = "r3d3", updEmail = "r3d3@gmail.com", updDate =
	// "1991-05-12";
	// EmployeeDto updEmployee = new EmployeeDto(id, updEmail, updName, updDate,
	// department.getId());
	// objectModel.updateEmployee(updEmployee);
	//
	// List<EmployeeDto> employees = objectModel.getEmployees();
	//
	// assertThat(employees.size()).isEqualTo(1);
	// EmployeeDto e = employees.get(0);
	// assertThat(e.getEmail()).isEqualTo(updEmail);
	// assertThat(e.getFullName()).isEqualTo(updName);
	// assertThat(e.getBirthday()).isEqualTo(updDate);
	// }
	//
	// @Test
	// public void testDeleteEmployee() {
	// String name = "R2&D2", email = "r2d2@gmail.com", date = "1981-05-12";
	// Department department = new Department(null, name);
	// em.persist(department);
	// Employee employee = new Employee(email, "r2d2", LocalDate.parse(date),
	// department);
	// em.persist(employee);
	// em.flush();
	//
	// UUID id = employee.getId();
	// objectModel.deleteEmployee(id);
	//
	// List<EmployeeDto> employees = objectModel.getEmployees();
	//
	// assertThat(employees.size()).isEqualTo(0);
	// }

}

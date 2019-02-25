package takeaway.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EmployeesAppl {
	public static void main(String[] args) {
		SpringApplication.run(EmployeesRouteSupplierService.class, args);
	}
}

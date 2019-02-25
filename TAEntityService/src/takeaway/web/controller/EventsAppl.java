package takeaway.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EventsAppl {
	public static void main(String[] args) {
		SpringApplication.run(EventsSupplierService.class, args);
	}
}

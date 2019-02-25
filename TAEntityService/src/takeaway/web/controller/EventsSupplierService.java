package takeaway.web.controller;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import takeaway.entities.dto.EventDto;
import takeaway.model.ObjectModel;

@SpringBootApplication
@RestController
@ImportResource({ "classpath:beans.xml" })
@ComponentScan({ "takeaway" })
public class EventsSupplierService {
	@Autowired
	ObjectModel objectModel;

	static final String queueName = "employee_queue";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	public void receiveMessage(String message) {
		EventDto event;
		try {
			event = new ObjectMapper().readValue(message, EventDto.class);
			objectModel.storeEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	MessageListenerAdapter listenerAdapter(EventsSupplierService receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@CrossOrigin
	@RequestMapping(value = "events", method = RequestMethod.GET)
	List<EventDto> getEvents() {
		return objectModel.getEvents();
	}
}
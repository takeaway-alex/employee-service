package takeaway.helpers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import takeaway.config.RabbitConfig;
import takeaway.entities.dto.EventDto;

@Component
public class RabbitHelper implements ApplicationContextAware {
	private static ApplicationContext context;
	static ObjectMapper mapper = new ObjectMapper();

	public void send(EventDto message) {
		AmqpTemplate template = context.getBean(AmqpTemplate.class);
		try {
			String jsonRes = mapper.writeValueAsString(message);
			template.convertAndSend(RabbitConfig.QUEUE_NAME, jsonRes);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		context = ac;
	}
}

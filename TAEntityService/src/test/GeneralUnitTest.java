package test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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

import takeaway.entities.Event;
import takeaway.entities.dto.EventDto;
import takeaway.model.ObjectModel;
import takeaway.web.controller.EventsAppl;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = EventsAppl.class)
@TestPropertySource(locations = "classpath:application.test.properties")
@ComponentScan({ "takeaway" })
public class GeneralUnitTest {
	@PersistenceContext(unitName = "springHibernate")
	private EntityManager em;

	@Autowired
	ObjectModel objectModel;

	@Test
	public void testGetEvents() {
		String action = "create";
		Event event = new Event(action, 123L);
		em.persist(event);
		em.flush();
		List<EventDto> events = objectModel.getEvents();

		assertThat(events.size()).isEqualTo(1);
		assertThat(events.get(0).getAction()).isEqualTo(action);
	}

	@Test
	public void testStoreEvent() {
		String action = "update";
		EventDto event = new EventDto(action);
		objectModel.storeEvent(event);
		List<EventDto> events = objectModel.getEvents();
		assertThat(events.size()).isEqualTo(1);
		assertThat(events.get(0).getAction()).isEqualTo(action);
	}
}

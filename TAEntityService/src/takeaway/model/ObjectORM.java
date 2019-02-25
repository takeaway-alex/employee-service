package takeaway.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import takeaway.entities.Event;
import takeaway.entities.dto.EventDto;

@Component
public class ObjectORM implements ObjectModel {
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;

	@Override
	public List<EventDto> getEvents() {
		List<Event> events = em.createQuery("SELECT e FROM Event e ORDER BY timestamp", Event.class).getResultList();
		List<EventDto> eventsDto = events.stream().map(event -> event.getEventDto()).collect(Collectors.toList());
		return eventsDto;
	}

	@Override
	@Transactional
	public void storeEvent(EventDto event) {
		Event e = new Event(event);
		em.persist(e);
	}
}

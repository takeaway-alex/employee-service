package takeaway.model;

import java.util.List;

import takeaway.entities.dto.EventDto;

public interface ObjectModel {
	List<EventDto> getEvents();

	void storeEvent(EventDto event);
}

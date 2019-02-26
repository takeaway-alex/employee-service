package takeaway.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import takeaway.entities.dto.EventDto;

@Entity
@Table(name = "event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String action;
	private Long timestamp;

	public Event() {
		super();
	}

	public Event(String action, Long timestamp) {
		super();
		this.action = action;
		this.timestamp = timestamp;
	}

	public Event(EventDto event) {
		super();
		this.action = event.getAction();
		this.timestamp = event.getTimestamp();
	}

	public EventDto getEventDto() {
		return new EventDto(action, timestamp);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
package takeaway.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {
	private String action;
	private Long timestamp;

	public EventDto() {
		super();
	}

	public EventDto(String action) {
		super();
		this.action = action;
		this.timestamp = System.currentTimeMillis();
	}

	public EventDto(String action, Long timestamp) {
		super();
		this.action = action;
		this.timestamp = timestamp;
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

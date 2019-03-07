package sample.models;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

	private String name;
	private String content;
	private LocalDateTime dateTime;


	public Message(String name, String content) {
		this.name = name;
		this.content = content;
		this.dateTime = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return String.format("[date: %s] %s writes: %s", dateTime, name, content);
	}
}

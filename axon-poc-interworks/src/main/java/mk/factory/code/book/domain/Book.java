package mk.factory.code.book.domain;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Book {
	@PrePersist
	public void init() {
		guid = UUID.randomUUID().toString();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column
	private String title;
	
	@Column(name = "GUID", updatable = false)
	private String guid;

	public String getGuid() {
		return guid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

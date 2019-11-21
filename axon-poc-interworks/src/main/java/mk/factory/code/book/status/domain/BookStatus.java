package mk.factory.code.book.status.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class BookStatus {
	@PrePersist
	public void init() {
		guid = UUID.randomUUID().toString();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column
	private String statusName;
	
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}

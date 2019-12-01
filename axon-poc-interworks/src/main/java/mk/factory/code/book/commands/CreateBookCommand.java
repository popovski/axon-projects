package mk.factory.code.book.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

// value object
public class CreateBookCommand {

	@TargetAggregateIdentifier
	private final String id;
	private final String title;
	private String guid;
	private String bookStatusGuid;
	
	public CreateBookCommand(String guid, String title, String bookStatusGuid) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.guid = guid;
		this.bookStatusGuid = bookStatusGuid;
		System.out.println("CreateBookCommand: bookStatusGuid" + bookStatusGuid);
	}

	public String getBookStatusGuid() {
		return bookStatusGuid;
	}

	public void setBookStatusGuid(String bookStatusGuid) {
		this.bookStatusGuid = bookStatusGuid;
	}

	public String getId() {
		return id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final CreateBookCommand other = (CreateBookCommand) obj;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return "CreateBookCommand{" + "book id='" + id + '\'' + ", title='" + '\'' + title + '}';
	}
}
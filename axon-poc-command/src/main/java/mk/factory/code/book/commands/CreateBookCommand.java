package mk.factory.code.book.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

// value object
public class CreateBookCommand {
	@TargetAggregateIdentifier
	private String guid;
	private final String title;
	private String bookStatusGuid;
	
	public CreateBookCommand(String guid, String title, String bookStatusGuid) {
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
		return Objects.hash(guid);
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
		return Objects.equals(this.guid, other.guid);
	}

	@Override
	public String toString() {
		return "CreateBookCommand{" + "book guid='" + guid + '\'' + ", title='" + '\'' + title + '}';
	}
}
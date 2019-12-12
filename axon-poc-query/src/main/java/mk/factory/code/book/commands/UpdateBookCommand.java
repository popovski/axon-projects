package mk.factory.code.book.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBookCommand {
	// this id should related to guid from the already created aggregate with the create command
	@TargetAggregateIdentifier
	private final String guid;
	private final String title;
	private final String bookStatusGuid;

	public UpdateBookCommand(String title, String guid, String bookStatusGuid) {
		this.title = title;
		this.guid = guid;
		this.bookStatusGuid = bookStatusGuid;
		System.out.println("CreateBookCommand: bookStatusGuid" + bookStatusGuid);
	}

	public String getBookStatusGuid() {
		return bookStatusGuid;
	}

	public String getGuid() {
		return guid;
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
		final UpdateBookCommand other = (UpdateBookCommand) obj;
		return Objects.equals(this.guid, other.guid);
	}

	@Override
	public String toString() {
		return "UpdateBookCommand{" + "guid ='" + guid + '\'' + ", title='" + '\'' + '}';
	}
}
package mk.factory.code.book.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBookCommand {

	@TargetAggregateIdentifier
	private final String id;
	private final String title;
	private final String guid;

	public UpdateBookCommand(String title, String guid) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}
	
	public String getId() {
		return id;
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
		final UpdateBookCommand other = (UpdateBookCommand) obj;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return "UpdateBookCommand{" + "id='" + id + '\'' + ", title='" + '\'' + '}';
	}
}
package mk.factory.code.book.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

// value object
public class CreateBookCommand {

	@TargetAggregateIdentifier
	private final String id;
	private final String title;
	
	public CreateBookCommand(String title) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
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
		final CreateBookCommand other = (CreateBookCommand) obj;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return "AddBookCommand{" + "book isbn='" + id + '\'' + ", title='" + '\'' + title + '}';
	}
}
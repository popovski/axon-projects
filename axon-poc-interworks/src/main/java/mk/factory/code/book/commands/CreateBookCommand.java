package mk.factory.code.book.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateBookCommand {

	@TargetAggregateIdentifier
	private final String isbn;
	private final String title;
	
	public CreateBookCommand(String title) {
		this.isbn = UUID.randomUUID().toString();
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
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
		return Objects.equals(this.isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "AddBookCommand{" + "book isbn='" + isbn + '\'' + ", title='" + '\'' + title + '}';
	}
}
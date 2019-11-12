package mk.factory.code.book.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBookCommand {

	@TargetAggregateIdentifier
	private final String isbn;
	private final String title;
	private final String id;

	public UpdateBookCommand(String isbn, String title, String id) {
		this.isbn = UUID.randomUUID().toString();
		this.title = title;
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public String getId() {
		return id;
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
		final UpdateBookCommand other = (UpdateBookCommand) obj;
		return Objects.equals(this.isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "PlaceOrderCommand{" + "orderId='" + isbn + '\'' + ", product='" + '\'' + '}';
	}
}
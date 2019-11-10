package mk.factory.code.book.commands;

import java.util.Objects;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AddBookCommand {

	@TargetAggregateIdentifier
	private final String isbn;

	public AddBookCommand(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
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
		final AddBookCommand other = (AddBookCommand) obj;
		return Objects.equals(this.isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "PlaceOrderCommand{" + "orderId='" + isbn + '\'' + ", product='" + '\'' + '}';
	}
}
package mk.factory.code.book.events;

import java.util.Objects;

public class AddBookEvent {
	private final String isbn;

    public AddBookEvent(String isbn) {
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
        final AddBookEvent other = (AddBookEvent) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    @Override
    public String toString() {
        return "OrderPlacedEvent{" +
                "isbn='" + isbn + '\'' +
                ", product='" + '\'' +
                '}';
    }
}
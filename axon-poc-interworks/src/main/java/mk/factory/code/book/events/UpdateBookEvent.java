package mk.factory.code.book.events;

import java.util.Objects;

import mk.factory.code.book.commands.AddBookCommand;
import mk.factory.code.book.commands.UpdateBookCommand;

public class UpdateBookEvent {
	private final String isbn;
	private final String title;
	private final String id;
	
    public UpdateBookEvent(UpdateBookCommand command) {
        this.isbn = command.getIsbn();
        this.title = command.getTitle();
        this.id = command.getId();
    }

    public String getIsbn() {
		return isbn;
	}
    
    public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
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
        final UpdateBookEvent other = (UpdateBookEvent) obj;
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
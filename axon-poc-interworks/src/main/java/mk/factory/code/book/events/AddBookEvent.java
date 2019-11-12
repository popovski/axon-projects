package mk.factory.code.book.events;

import java.util.Objects;

import mk.factory.code.book.commands.AddBookCommand;

public class AddBookEvent {
	private final String isbn;
	private final String id;
	private final String title;
	
    public AddBookEvent(AddBookCommand command) {
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
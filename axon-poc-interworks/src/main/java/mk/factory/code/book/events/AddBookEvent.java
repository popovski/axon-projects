package mk.factory.code.book.events;

import java.util.Objects;

import mk.factory.code.book.commands.AddBookCommand;

public class AddBookEvent {
	private final String isbn;
	private final String title;
	
    public AddBookEvent(AddBookCommand command) {
        this.isbn = command.getIsbn();
        this.title = command.getTitle();
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
        final AddBookEvent other = (AddBookEvent) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    @Override
    public String toString() {
        return "AddBookEvent{" +
                "isbn='" + isbn + '\'' +
                ", product='" + '\'' +
                '}';
    }
}
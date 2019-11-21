package mk.factory.code.book.events;

import java.util.Objects;

import mk.factory.code.book.commands.CreateBookCommand;

public class CreateBookEvent {
	private final String id;
	private final String title;
	
    public CreateBookEvent(CreateBookCommand command) {
        this.id = command.getId();
        this.title = command.getTitle();
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
        final CreateBookEvent other = (CreateBookEvent) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "AddBookEvent{" +
                "id='" + id + '\'' +
                ", ='" + '\'' +
                '}';
    }
}
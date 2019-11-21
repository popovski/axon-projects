package mk.factory.code.book.status.events;

import java.util.Objects;

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.status.commands.CreateBookStatusCommand;

public class CreateBookStatusEvent {
	private final String id;
	private final String statusName;
	
    public CreateBookStatusEvent(CreateBookStatusCommand command) {
        this.id = command.getId();
        this.statusName = command.getStatusName();
    }

    public String getId() {
		return id;
	}

	public String getStatusName() {
		return statusName;
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
        final CreateBookStatusEvent other = (CreateBookStatusEvent) obj;
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
package mk.factory.code.book.events;

import java.util.Objects;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBookEvent {
	@TargetAggregateIdentifier
	private final String guid;
	private final String title;
	private final String bookStatusGuid;
	
	public UpdateBookEvent(String title, String guid, String bookStatusGuid) {
        this.title = title;
        this.guid = guid;
        this.bookStatusGuid = bookStatusGuid;
    }

    public String getBookStatusGuid() {
		return bookStatusGuid;
	}

    public String getGuid() {
		return guid;
	}

    
    public String getTitle() {
		return title;
	}

    @Override
    public int hashCode() {
        return Objects.hash(guid);
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
        return Objects.equals(this.guid, other.guid);
    }

    @Override
    public String toString() {
        return "UpdateBookEvent{" +
                "id='" + guid + '\'' +
                ", product='" + '\'' +
                '}';
    }
}
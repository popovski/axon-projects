package mk.factory.code.book.events;

import java.util.Objects;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBookEvent {
	@TargetAggregateIdentifier
	private final String id;
	private final String title;
	private final String guid;
	
	public UpdateBookEvent(String id, String title, String guid) {
        this.id = id;
        this.title = title;
        this.guid = guid;
    }

    public String getId() {
		return id;
	}

    public String getGuid() {
		return guid;
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
        final UpdateBookEvent other = (UpdateBookEvent) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "UpdateBookEvent{" +
                "id='" + id + '\'' +
                ", product='" + '\'' +
                '}';
    }
}
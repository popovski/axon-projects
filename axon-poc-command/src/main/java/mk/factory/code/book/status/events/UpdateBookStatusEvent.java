package mk.factory.code.book.status.events;

import java.util.Objects;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBookStatusEvent {
	@TargetAggregateIdentifier
	private final String id;
	private final String statusName;
	private final String guid;
	
	public UpdateBookStatusEvent(String id, String statusName, String guid) {
        this.id = id;
        this.statusName = statusName;
        this.guid = guid;
    }

    public String getId() {
		return id;
	}

    public String getGuid() {
		return guid;
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
        final UpdateBookStatusEvent other = (UpdateBookStatusEvent) obj;
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
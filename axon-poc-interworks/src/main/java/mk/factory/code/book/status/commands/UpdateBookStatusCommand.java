package mk.factory.code.book.status.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBookStatusCommand {

	@TargetAggregateIdentifier
	private final String id;
	private final String statusName;
	private final String guid;

	public UpdateBookStatusCommand(String statusName, String guid) {
		this.id = UUID.randomUUID().toString();
		this.statusName = statusName;
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}
	
	public String getId() {
		return id;
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
		final UpdateBookStatusCommand other = (UpdateBookStatusCommand) obj;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return "UpdateBookCommand{" + "id='" + id + '\'' + ", status name='" + '\'' + '}';
	}

	public String getStatusName() {
		return statusName;
	}
}
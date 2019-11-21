package mk.factory.code.book.status.commands;

import java.util.Objects;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

// value object
public class CreateBookStatusCommand {

	@TargetAggregateIdentifier
	private final String id;
	private final String statusName;
	
	public CreateBookStatusCommand(String statusName) {
		this.id = UUID.randomUUID().toString();
		this.statusName = statusName;
	}

	public String getStatusName() {
		return statusName;
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
		final CreateBookStatusCommand other = (CreateBookStatusCommand) obj;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return "CreateBookStatusCommand{" + "book id='" + id + '\'' + ", statusName='" + '\'' + statusName + '}';
	}
}
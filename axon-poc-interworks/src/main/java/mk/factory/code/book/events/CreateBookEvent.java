package mk.factory.code.book.events;

import java.util.Objects;

import mk.factory.code.book.commands.CreateBookCommand;

public class CreateBookEvent {
	private final String id;
	private String guid;
	private final String title;
	private String bookStatusGuid;
	
    public CreateBookEvent(CreateBookCommand command) {
        this.id = command.getId();
        this.guid = command.getGuid();
        this.title = command.getTitle();
        this.bookStatusGuid = command.getBookStatusGuid();
    }

    public String getBookStatusGuid() {
		return bookStatusGuid;
	}

	public void setBookStatusGuid(String bookStatusGuid) {
		this.bookStatusGuid = bookStatusGuid;
	}

	public String getId() {
		return id;
	}
    
    public String getTitle() {
		return title;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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
        return "CreateBookEvent {" + "id='" + id + '\'' +
                ", ='" + '\'' +
                '}';
    }
}
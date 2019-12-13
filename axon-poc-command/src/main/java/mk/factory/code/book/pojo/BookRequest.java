package mk.factory.code.book.pojo;
// DTO object handles request payload
public class BookRequest {
	private String title;
	private String bookStatusGuid;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBookStatusGuid() {
		return bookStatusGuid;
	}

	public void setBookStatusGuid(String bookStatusGuid) {
		this.bookStatusGuid = bookStatusGuid;
	}
}

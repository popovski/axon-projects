package mk.factory.code.book.pojo;
// DTO object handles request payload
public class BookRequest {
	private String title;
	private String bookStatusGuid;
	// book guid id will be created by the http client e.x(fe or postman)
	private String guid;

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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}

package mk.factory.code.book.status.pojo;
// DTO object used to handle request payload
public class BookStatusRequest {
	private String statusName;
	private String guid;
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}

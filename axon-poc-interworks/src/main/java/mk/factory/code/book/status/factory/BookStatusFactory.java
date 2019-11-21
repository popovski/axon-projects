package mk.factory.code.book.status.factory;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.domain.Book;
import mk.factory.code.book.events.CreateBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.pojo.BookRequest;
import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.status.domain.BookStatus;
import mk.factory.code.book.status.events.CreateBookStatusEvent;
import mk.factory.code.book.status.events.UpdateBookStatusEvent;
import mk.factory.code.book.status.pojo.BookStatusResponse;

@Component
public class BookStatusFactory {
	public BookStatus createBookStatusEntity(CreateBookStatusEvent event) {
		BookStatus bookStatus = new BookStatus();
		bookStatus.setStatusName(event.getStatusName());
		
		return bookStatus;
	}
	
	public BookStatus createBookStatusEntity(UpdateBookStatusEvent event) {
		BookStatus bookStatus = new BookStatus();
		bookStatus.setStatusName(event.getStatusName());
		bookStatus.setId(event.getId());
		
		return bookStatus;
	}
	
	public Function<BookStatus, BookStatusResponse> toBookStatusDTO() {
		return bookStatusEntity -> {
			BookStatusResponse bookStatusResponse = new BookStatusResponse();
			bookStatusResponse.setGuid(bookStatusEntity.getGuid());
			bookStatusResponse.setStatusName(bookStatusEntity.getStatusName());
			return bookStatusResponse;
		};
	}
}

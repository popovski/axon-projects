package mk.factory.code.book.factory;

import java.util.function.Function;
import org.springframework.stereotype.Component;
import mk.factory.code.book.domain.BookEntity;
import mk.factory.code.book.events.CreateBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.status.domain.BookStatusEntity;

@Component
public class BookFactory {
	// used for creating new entity
	public BookEntity createBookEntity(CreateBookEvent event, BookStatusEntity bookStatus) {
		// get the status object from db
		BookEntity book = new BookEntity();
		book.setTitle(event.getTitle());
		book.setGuid(event.getGuid());
		book.setBookStatus(bookStatus);

		return book;
	}

	// used for updating book entity
	public BookEntity createBookEntity(UpdateBookEvent event, BookEntity bookEntity, BookStatusEntity bookStatus) {

		bookEntity.setTitle(event.getTitle());
		bookEntity.setBookStatus(bookStatus);

		return bookEntity;
	}

	public Function<BookEntity, BookResponse> toBookDTO() {
		return bookEntity -> {
			BookResponse bookResponse = new BookResponse();
			bookResponse.setGuid(bookEntity.getGuid());
			bookResponse.setTitle(bookEntity.getTitle());
			bookResponse.setCreateDateTime(bookEntity.getCreateDateTime());
			bookResponse.setUpdateDateTime(bookEntity.getUpdateDateTime());
			bookResponse.setBookStatusGuid(bookEntity.getBookStatus().getGuid());

			return bookResponse;
		};
	}
}

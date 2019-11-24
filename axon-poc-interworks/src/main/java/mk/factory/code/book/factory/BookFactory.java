package mk.factory.code.book.factory;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.domain.BookEntity;
import mk.factory.code.book.events.CreateBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.pojo.BookRequest;
import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.status.domain.BookStatusEntity;

@Component
public class BookFactory {
	public BookEntity createBookEntity(CreateBookEvent event, BookStatusEntity bookStatus) {
		// get the status object from db
		BookEntity book = new BookEntity();
		book.setTitle(event.getTitle());
		book.setGuid(UUID.randomUUID().toString());
		book.setBookStatus(bookStatus);
		
		return book;
	}
	
	public BookEntity createBookEntity(UpdateBookEvent event, 
			BookEntity bookEntity, BookStatusEntity bookStatus) {
		if (bookEntity != null) {
			bookEntity.setTitle(event.getTitle());
			bookEntity.setBookStatus(bookStatus);
		} else {
			bookEntity = new BookEntity();
			bookEntity.setTitle(event.getTitle());
			bookEntity.setBookStatus(bookStatus);
			bookEntity.setGuid(event.getGuid());
		}
		return bookEntity;
	}
	
	public Function<BookEntity, BookResponse> toBookDTO() {
		return bookEntity -> {
			BookResponse bookResponse = new BookResponse();
			if (bookEntity != null) {
				bookResponse.setGuid(bookEntity.getGuid());
				bookResponse.setTitle(bookEntity.getTitle());
				bookResponse.setCreateDateTime(bookEntity.getCreateDateTime());
				bookResponse.setUpdateDateTime(bookEntity.getUpdateDateTime());
			}
			
			if (bookEntity.getBookStatus() != null) {
				bookResponse.setBookStatusGuid(bookEntity.getBookStatus().getGuid());
			}
			
			return bookResponse;
		};
	}
}

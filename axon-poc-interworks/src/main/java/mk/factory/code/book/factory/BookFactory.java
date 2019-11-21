package mk.factory.code.book.factory;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.domain.Book;
import mk.factory.code.book.events.AddBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.pojo.BookRequest;
import mk.factory.code.book.pojo.BookResponse;

@Component
public class BookFactory {
	public Book createBookEntity(AddBookEvent event) {
		Book book = new Book();
		book.setTitle(event.getTitle());
		
		return book;
	}
	
	public Book createBookEntity(UpdateBookEvent event) {
		Book book = new Book();
		
		book.setTitle(event.getTitle());
		book.setId(event.getId());
		
		return book;
	}
	
	public Function<Book, BookResponse> toBookDTO() {
		return bookEntity -> {
			BookResponse bookResponse = new BookResponse();
			bookResponse.setGuid(bookEntity.getGuid());
			bookResponse.setTitle(bookEntity.getTitle());
			return bookResponse;
		};
	}
}

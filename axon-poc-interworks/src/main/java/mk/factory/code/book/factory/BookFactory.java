package mk.factory.code.book.factory;

import org.springframework.stereotype.Component;

import mk.factory.code.book.domain.Book;
import mk.factory.code.book.events.AddBookEvent;

@Component
public class BookFactory {
	public Book createBookEntity(AddBookEvent event) {
		Book book = new Book();
		book.setIsbn(event.getIsbn());
		
		return book;
	}
}

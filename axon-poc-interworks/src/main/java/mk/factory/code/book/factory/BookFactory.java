package mk.factory.code.book.factory;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import mk.factory.code.book.domain.Book;
import mk.factory.code.book.events.AddBookEvent;
import mk.factory.code.book.queries.BookDTO;

@Component
public class BookFactory {
	public Book createBookEntity(AddBookEvent event) {
		Book book = new Book();
		book.setIsbn(event.getIsbn());
		
		return book;
	}
	
	public Function<Book, BookDTO> toBookDTO() {
		return e -> {
			BookDTO book = new BookDTO();
			book.setIsbn(e.getIsbn());
			return book;
		};
	}
}

package mk.factory.code.book.projector;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.factory.code.book.events.AddBookEvent;
import mk.factory.code.book.factory.BookFactory;
import mk.factory.code.book.queries.BookDTO;
import mk.factory.code.book.queries.FindAllBooksQuery;
import mk.factory.code.book.repository.BookRepository;
import java.util.stream.Collectors;

@Service
public class BookEventProjector {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookFactory bookFactory;

	@EventHandler
	public void on(AddBookEvent event) {
		bookRepository.save(bookFactory.createBookEntity(event));
	}

	@QueryHandler
	public List<BookDTO> handle(FindAllBooksQuery query) {
		return bookRepository.findAll().stream().map(bookFactory.toBookDTO()).collect(Collectors.toList());
	}
}
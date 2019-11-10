package mk.factory.code.book.projector;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.factory.code.book.domain.Book;
import mk.factory.code.book.events.AddBookEvent;
import mk.factory.code.book.factory.BookFactory;
import mk.factory.code.book.repository.BookRepository;

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

}
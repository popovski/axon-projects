package mk.factory.code.book.projector;

import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ReplayStatus;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.factory.code.book.events.AddBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.factory.BookFactory;
import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.queries.FindAllBooksQuery;
import mk.factory.code.book.repository.BookRepository;
import java.util.stream.Collectors;

@Service
@ProcessingGroup("book")
public class BookEventProjector {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookFactory bookFactory;

	@EventHandler
	public void on(AddBookEvent event) {
		bookRepository.save(bookFactory.createBookEntity(event));
	}
	
	@EventHandler
	@AllowReplay(true)
	public void on(UpdateBookEvent event, ReplayStatus replayStatus) {
		bookRepository.save(bookFactory.createBookEntity(event));
	}

	@QueryHandler
	public List<BookResponse> handle(FindAllBooksQuery query) {
		return bookRepository.findAll().stream().map(bookFactory.toBookDTO()).collect(Collectors.toList());
	}
	
    @ResetHandler
    public void onReset() { 
    	bookRepository.deleteAll(); 
    }
}
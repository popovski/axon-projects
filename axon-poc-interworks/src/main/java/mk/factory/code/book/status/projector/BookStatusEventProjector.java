package mk.factory.code.book.status.projector;

import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ReplayStatus;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.factory.code.book.events.CreateBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.factory.BookFactory;
import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.queries.FindAllBooksQuery;
import mk.factory.code.book.repository.BookRepository;
import mk.factory.code.book.status.events.CreateBookStatusEvent;
import mk.factory.code.book.status.events.UpdateBookStatusEvent;
import mk.factory.code.book.status.factory.BookStatusFactory;
import mk.factory.code.book.status.pojo.BookStatusResponse;
import mk.factory.code.book.status.queries.FindAllBookStatusQuery;
import mk.factory.code.book.status.repository.BookStatusRepository;

import java.util.stream.Collectors;

@Service
@ProcessingGroup("bookStatus")
public class BookStatusEventProjector {
	Logger logger = LoggerFactory.getLogger(BookStatusEventProjector.class);
	
	@Autowired
	private BookStatusRepository bookStatusRepository;
	
	@Autowired
	private BookStatusFactory bookStatusFactory;

	@EventHandler
	@AllowReplay(true)
	public void on(CreateBookStatusEvent event, ReplayStatus replayStatus) {
		logger.info("Replay Status: {}", replayStatus.isReplay());
		logger.info("CreateBookStatusEvent: GUID {}", event.getGuid());
		logger.info("CreateBookStatusEvent: StatusName {}", event.getStatusName());
		bookStatusRepository.save(bookStatusFactory.createBookStatusEntity(event));
	}
	
	@EventHandler
	@AllowReplay(true)
	public void on(UpdateBookStatusEvent event, ReplayStatus replayStatus) {
		logger.info("Replay Status: {}", replayStatus.isReplay());
		logger.info("UpdateBookStatusEvent: GUID {}", event.getGuid());
		logger.info("UpdateBookStatusEvent: StatusName {}", event.getStatusName());
		bookStatusRepository.save(bookStatusFactory.createBookStatusEntity(event));
	}

	@QueryHandler
	public List<BookStatusResponse> handle(FindAllBookStatusQuery query) {
		return bookStatusRepository.findAll()
				.stream().map(bookStatusFactory.toBookStatusDTO()).collect(Collectors.toList());
	}
	
    @ResetHandler
    public void onReset() { 
    	bookStatusRepository.deleteAll(); 
    }
}
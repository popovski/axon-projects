package mk.factory.code.book.projector;

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

import mk.factory.code.book.domain.BookEntity;
import mk.factory.code.book.events.CreateBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.factory.BookFactory;
import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.queries.FindAllBooksQuery;
import mk.factory.code.book.repository.BookRepository;
import mk.factory.code.book.status.domain.BookStatusEntity;
import mk.factory.code.book.status.repository.BookStatusRepository;

import java.util.stream.Collectors;

@Service
@ProcessingGroup("book")
public class BookEventProjector {
	Logger logger = LoggerFactory.getLogger(BookEventProjector.class);
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookStatusRepository bookStatusRepository;
	
	@Autowired
	private BookFactory bookFactory;

	@EventHandler
	@AllowReplay(false)
	public void on(CreateBookEvent event) {
		logger.info("CreateBookEvent: BOOK GUID {}", event.getGuid());
		logger.info("CreateBookEvent: TITLE {}", event.getTitle());
		logger.info("CreateBookEvent: BOOK STATUS GUID {}", event.getBookStatusGuid());
		BookStatusEntity bookStatus = bookStatusRepository.findByGuid(event.getBookStatusGuid());
		
		bookRepository.save(bookFactory.createBookEntity(event, bookStatus));
	}
	
	@EventHandler
	@AllowReplay(true)
	public void on(UpdateBookEvent event, ReplayStatus replayStatus) {
		logger.info("Replay Status: {}", replayStatus.isReplay());
		logger.info("UpdateBookEvent: BOOK GUID {}", event.getGuid());
		logger.info("UpdateBookEvent: TITLE {}", event.getTitle());
		logger.info("UpdateBookEvent: BOOK STATUS GUID {}", event.getBookStatusGuid());
		BookStatusEntity bookStatus = bookStatusRepository.findByGuid(event.getBookStatusGuid());
		
		if (bookStatus != null) {
			logger.info("UpdateBookEvent: BOOK STATUS TITLE {}", bookStatus.getStatusName());
		} else {
			logger.error("Error: bookStatus is not existing");
		}
		
		BookEntity bookEntity = bookRepository.findByGuid(event.getGuid());
		
		bookRepository.save(bookFactory.createBookEntity(event, bookEntity, bookStatus));
	}

	@QueryHandler
	public List<BookResponse> handle(FindAllBooksQuery query) {
		return bookRepository.findAll().stream().map(bookFactory.toBookDTO()).collect(Collectors.toList());
	}
}
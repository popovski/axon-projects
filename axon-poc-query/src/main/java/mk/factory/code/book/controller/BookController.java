package mk.factory.code.book.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.projector.AxonAdministration;
import mk.factory.code.book.queries.FindAllBooksQuery;
import mk.factory.code.core.RestEndpoints;

@RestController
@RequestMapping(RestEndpoints.BOOK)
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	private final QueryGateway queryGateway;

	@Autowired
	AxonAdministration axonAdministration;
	// objects are injected by sprint axon support
	public BookController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@GetMapping()
	public List<BookResponse> findAllBooks() {
		return queryGateway.query(new FindAllBooksQuery(), 
				ResponseTypes.multipleInstancesOf(BookResponse.class)).join();
	}
	
	@PostMapping("/{groupName}/{minutes}/replay")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<Object> replay(@PathVariable String groupName, 
			@PathVariable Integer minutes) {

		axonAdministration.resetTrackingEventProcessorByDays(groupName, minutes);

		return ResponseEntity.accepted().build();
	}
}

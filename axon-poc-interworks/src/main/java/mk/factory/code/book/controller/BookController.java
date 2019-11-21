package mk.factory.code.book.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.commands.UpdateBookCommand;
import mk.factory.code.book.factory.BookFactory;
import mk.factory.code.book.pojo.BookRequest;
import mk.factory.code.book.pojo.BookResponse;
import mk.factory.code.book.projector.AxonAdministration;
import mk.factory.code.book.queries.FindAllBooksQuery;
import mk.factory.code.core.RestEndpoints;

@RestController
@RequestMapping(RestEndpoints.BOOK)
public class BookController {
	private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

	@Autowired
	AxonAdministration axonAdministration;
	@Autowired
	BookFactory bookFactory;

	public BookController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createBook(@RequestBody BookRequest bookRequest) {
		LOG.info("Create Book Request");
		commandGateway.send(new CreateBookCommand(bookRequest.getTitle()));
	}

	@PutMapping("/{guid}")
	// update using the guid as a path param
	public void updateBook(@RequestBody UpdateBookCommand updateBookCommand) {
		commandGateway.send(updateBookCommand);
	}

	@GetMapping()
	public List<BookResponse> findAllBooks() {
		return queryGateway.query(new FindAllBooksQuery(), ResponseTypes.multipleInstancesOf(BookResponse.class)).join();
	}

	@PostMapping("/{groupName}/replay")
	public ResponseEntity<Object> replay(@PathVariable String groupName) {

		axonAdministration.resetTrackingEventProcessor(groupName);

		return ResponseEntity.accepted().build();
	}
}
package mk.factory.code.book.status.controller;

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
import mk.factory.code.book.status.commands.CreateBookStatusCommand;
import mk.factory.code.book.status.commands.UpdateBookStatusCommand;
import mk.factory.code.book.status.factory.BookStatusFactory;
import mk.factory.code.book.status.pojo.BookStatusRequest;
import mk.factory.code.book.status.pojo.BookStatusResponse;
import mk.factory.code.book.status.queries.FindAllBookStatusQuery;
import mk.factory.code.core.RestEndpoints;

@RestController
@RequestMapping(RestEndpoints.BOOK_STATUS)
public class BookStatusController {
	private static final Logger LOG = LoggerFactory.getLogger(BookStatusController.class);

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

	@Autowired
	BookStatusFactory bookStatusFactory;

	public BookStatusController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createBookStatus(@RequestBody BookStatusRequest bookStatusRequest) {
		LOG.info("Create Book Status Request");
		commandGateway.send(new CreateBookStatusCommand(bookStatusRequest.getGuid(), 
				bookStatusRequest.getStatusName()));
	}

	@PutMapping("/{guid}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void updateBook(@RequestBody BookStatusRequest bookStatusRequest, @PathVariable String guid) {
		commandGateway.send(new UpdateBookStatusCommand(bookStatusRequest.getStatusName(), guid));
	}

	@GetMapping()
	public List<BookStatusResponse> findAllBookStatus() {
		return queryGateway.query(new FindAllBookStatusQuery(), 
				ResponseTypes.multipleInstancesOf(BookStatusResponse.class)).join();
	}
}

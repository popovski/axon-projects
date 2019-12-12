package mk.factory.code.book.status.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.factory.code.book.status.commands.CreateBookStatusCommand;
import mk.factory.code.book.status.commands.UpdateBookStatusCommand;
import mk.factory.code.book.status.pojo.BookStatusRequest;
import mk.factory.code.core.RestEndpoints;

@RestController
@RequestMapping(RestEndpoints.BOOK_STATUS)
public class BookStatusController {
	private static final Logger LOG = LoggerFactory.getLogger(BookStatusController.class);

	private final CommandGateway commandGateway;

	// objects are injected by sprint axon support
	public BookStatusController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
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
}

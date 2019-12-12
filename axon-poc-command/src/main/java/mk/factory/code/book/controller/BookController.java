package mk.factory.code.book.controller;

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

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.commands.UpdateBookCommand;
import mk.factory.code.book.pojo.BookRequest;
import mk.factory.code.core.RestEndpoints;

@RestController
@RequestMapping(RestEndpoints.BOOK)
public class BookController {
	private static final Logger LOG = LoggerFactory.getLogger(BookController.class);
	private final CommandGateway commandGateway;

	// objects are injected by sprint axon support
	public BookController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createBook(@RequestBody BookRequest bookRequest) {
		LOG.info("Create Book Request");
		commandGateway.send(new CreateBookCommand(bookRequest.getGuid(), 
				bookRequest.getTitle(), bookRequest.getBookStatusGuid()));
	}

	@PutMapping("/{guid}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void updateBook(@RequestBody BookRequest bookRequest, @PathVariable String guid) {
		commandGateway.send(new UpdateBookCommand(bookRequest.getTitle(), guid, bookRequest.getBookStatusGuid()));
	}
}

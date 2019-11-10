package mk.factory.code.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.factory.code.book.commands.AddBookCommand;

@RestController
public class BookController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public BookController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/book")
    public void addBook() {
        String isbn = UUID.randomUUID().toString();
        commandGateway.send(new AddBookCommand(isbn));
    }

}

package mk.factory.code.axon.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.factory.code.axon.coreapi.commands.PlaceOrderCommand;

@RestController
public class OrderRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public OrderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/book")
    public void addBook() {
        String isbn = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(isbn, "Deluxe Chair"));
    }

}

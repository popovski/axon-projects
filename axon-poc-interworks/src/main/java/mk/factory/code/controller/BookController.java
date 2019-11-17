package mk.factory.code.controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mk.factory.code.book.commands.AddBookCommand;
import mk.factory.code.book.commands.UpdateBookCommand;
import mk.factory.code.book.projector.AxonAdministration;
import mk.factory.code.book.queries.BookDTO;
import mk.factory.code.book.queries.FindAllBooksQuery;

@RestController
public class BookController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    AxonAdministration axonAdministration;
    
    public BookController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/book")
    public void addBook(@RequestBody AddBookCommand bookCommand) {
        commandGateway.send(bookCommand);
    }
    
    @PutMapping("/book")
    public void updateBook(@RequestBody UpdateBookCommand bookCommand) {
        commandGateway.send(bookCommand);
    }

    @GetMapping("/book")
    public List<BookDTO> findAllBooks() {
        return queryGateway.query(new FindAllBooksQuery(), 
        		ResponseTypes.multipleInstancesOf(BookDTO.class)).join();
    }
    
    @PostMapping("/book/{groupName}/replay")
    public  ResponseEntity<Object> replay(@PathVariable String groupName) {
    	
    	axonAdministration.resetTrackingEventProcessor(groupName);
    	
    	return ResponseEntity.accepted().build();
    }
}

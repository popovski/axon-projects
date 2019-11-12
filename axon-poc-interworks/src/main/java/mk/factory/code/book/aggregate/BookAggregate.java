package mk.factory.code.book.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import mk.factory.code.book.commands.AddBookCommand;
import mk.factory.code.book.commands.UpdateBookCommand;
import mk.factory.code.book.events.AddBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;

@Aggregate
public class BookAggregate {

    @AggregateIdentifier
    private String isbn;
    private String id;
    private String title;
    
    protected BookAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
    
    @CommandHandler
    public BookAggregate(AddBookCommand command) {
    	AggregateLifecycle.apply(new AddBookEvent(command));
    }

    @CommandHandler
    public BookAggregate(UpdateBookCommand command) {
    	AggregateLifecycle.apply(new UpdateBookEvent(command));
    }
    
    @EventSourcingHandler
    public void on(AddBookEvent event) {
        this.isbn = event.getIsbn();
        this.title = event.getTitle();
        this.id = event.getId();
    }
    
    @EventSourcingHandler
    public void on(UpdateBookEvent event) {
    	this.isbn = event.getIsbn();
        this.title = event.getTitle();
        this.id = event.getId();
    }
}
package mk.factory.code.book.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.commands.UpdateBookCommand;
import mk.factory.code.book.events.CreateBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;

@Aggregate
public class BookAggregate {

    @AggregateIdentifier
    private String id;
    private String title;
    private String guid;
	private String bookStatusGuid;
    
    protected BookAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
    
    @CommandHandler
    public BookAggregate(CreateBookCommand command) {
    	AggregateLifecycle.apply(new CreateBookEvent(command));
    }

    @CommandHandler
    public BookAggregate(UpdateBookCommand command) {
    	AggregateLifecycle.apply(new UpdateBookEvent(command.getId(), 
    			command.getTitle(), command.getGuid(), command.getBookStatusGuid()));
    }
    
    @EventSourcingHandler
    public void on(CreateBookEvent event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.bookStatusGuid = event.getBookStatusGuid();
    }
    
    @EventSourcingHandler
    public void on(UpdateBookEvent event) {
    	this.id = event.getId();
        this.title = event.getTitle();
        this.guid = event.getGuid();
        this.bookStatusGuid = event.getBookStatusGuid();
    }
}
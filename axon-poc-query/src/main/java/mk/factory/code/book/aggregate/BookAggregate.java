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
    private String guid;
    private String title;
	private String bookStatusGuid;
    
	public BookAggregate() {
		
	}
	
    @CommandHandler
    public BookAggregate(CreateBookCommand command) {
    	AggregateLifecycle.apply(new CreateBookEvent(command));
    }
    // update book case should not be handled in the constructor
    // because we have to connect the already existing book aggregate with the update book command
    @CommandHandler
    public void handle(UpdateBookCommand command) {
    	AggregateLifecycle.apply(new UpdateBookEvent(command.getTitle(), 
    			command.getGuid(), command.getBookStatusGuid()));
    }

    @EventSourcingHandler
    public void on(CreateBookEvent event) {
        this.guid = event.getGuid();
        this.title = event.getTitle();
        this.bookStatusGuid = event.getBookStatusGuid();
    }
    
    @EventSourcingHandler
    public void on(UpdateBookEvent event) {
    	 this.guid = event.getGuid();
    	this.title = event.getTitle();
        this.bookStatusGuid = event.getBookStatusGuid();
    }
}
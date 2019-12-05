package mk.factory.code.book.status.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.commands.UpdateBookCommand;
import mk.factory.code.book.events.CreateBookEvent;
import mk.factory.code.book.events.UpdateBookEvent;
import mk.factory.code.book.status.commands.CreateBookStatusCommand;
import mk.factory.code.book.status.commands.UpdateBookStatusCommand;
import mk.factory.code.book.status.events.CreateBookStatusEvent;
import mk.factory.code.book.status.events.UpdateBookStatusEvent;

@Aggregate
public class BookStatusAggregate {

    @AggregateIdentifier
    private String guid;
    private String statusName;
    
    protected BookStatusAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
    // handle the command and create book status event
    @CommandHandler
    public BookStatusAggregate(CreateBookStatusCommand command) {
    	// send event to the event store
    	// the event is handled by the projector service
    	AggregateLifecycle.apply(new CreateBookStatusEvent(command));
    }
    // update command should not be in a constructor
    @CommandHandler
    public void handle(UpdateBookStatusCommand command) {
    	AggregateLifecycle.apply(new UpdateBookStatusEvent(command.getId(), command.getStatusName(), command.getGuid()));
    }
    // AggregateIdentifier should be set only when we create entity
    @EventSourcingHandler
    public void on(CreateBookStatusEvent event) {
    	this.guid = event.getGuid();
        this.statusName = event.getStatusName();
    }
    // AggregateIdentifier should be reused when we update one    
    @EventSourcingHandler
    public void on(UpdateBookStatusEvent event) {
        this.statusName = event.getStatusName();
    }
}
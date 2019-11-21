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
    private String id;
    private String statusName;
    private String guid;
    
    protected BookStatusAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
    
    @CommandHandler
    public BookStatusAggregate(CreateBookStatusCommand command) {
    	AggregateLifecycle.apply(new CreateBookStatusEvent(command));
    }

    @CommandHandler
    public BookStatusAggregate(UpdateBookStatusCommand command) {
    	AggregateLifecycle.apply(new UpdateBookStatusEvent(command.getId(), command.getStatusName(), command.getGuid()));
    }
    
    @EventSourcingHandler
    public void on(CreateBookStatusEvent event) {
        this.id = event.getId();
        this.statusName = event.getStatusName();
    }
    
    @EventSourcingHandler
    public void on(UpdateBookStatusEvent event) {
    	this.id = event.getId();
        this.statusName = event.getStatusName();
        this.guid = event.getGuid();
    }
}
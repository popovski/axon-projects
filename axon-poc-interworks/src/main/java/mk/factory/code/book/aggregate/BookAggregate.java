package mk.factory.code.book.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import mk.factory.code.book.commands.AddBookCommand;
import mk.factory.code.book.events.AddBookEvent;

@Aggregate
public class BookAggregate {

    @AggregateIdentifier
    private String orderId;

    @CommandHandler
    public BookAggregate(AddBookCommand command) {
    	AggregateLifecycle.apply(new AddBookEvent(command.getIsbn()));
    }

    @EventSourcingHandler
    public void on(AddBookEvent event) {
        this.orderId = event.getIsbn();
    }

    protected BookAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
}
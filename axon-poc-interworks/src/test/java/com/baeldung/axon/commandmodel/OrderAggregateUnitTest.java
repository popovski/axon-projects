package com.baeldung.axon.commandmodel;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.*;

import mk.factory.code.book.aggregate.BookAggregate;
import mk.factory.code.book.commands.AddBookCommand;
import mk.factory.code.book.events.AddBookEvent;

public class OrderAggregateUnitTest {

    private FixtureConfiguration<BookAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(BookAggregate.class);
    }

    @Test
    public void giveNoPriorActivity_whenPlaceOrderCommand_thenShouldPublishOrderPlacedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.givenNoPriorActivity()
               .when(new AddBookCommand(orderId, "", ""))
               .expectEvents(new AddBookEvent(new AddBookCommand(orderId, "", "")));
    }

}
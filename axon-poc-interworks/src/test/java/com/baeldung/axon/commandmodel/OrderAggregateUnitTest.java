package com.baeldung.axon.commandmodel;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.*;

import mk.factory.code.book.aggregate.BookAggregate;
import mk.factory.code.book.commands.CreateBookCommand;
import mk.factory.code.book.events.CreateBookEvent;

@Ignore
public class OrderAggregateUnitTest {

    private FixtureConfiguration<BookAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(BookAggregate.class);
    }

    @Test
    public void giveNoPriorActivity_whenPlaceOrderCommand_thenShouldPublishOrderPlacedEvent() {
        fixture.givenNoPriorActivity()
               .when(new CreateBookCommand("", null))
               .expectEvents(new CreateBookEvent(new CreateBookCommand("", null)));
    }

}
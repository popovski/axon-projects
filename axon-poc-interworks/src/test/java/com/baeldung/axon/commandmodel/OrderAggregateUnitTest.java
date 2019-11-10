package com.baeldung.axon.commandmodel;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.*;

import mk.factory.code.axon.commandmodel.OrderAggregate;
import mk.factory.code.axon.coreapi.commands.PlaceOrderCommand;
import mk.factory.code.axon.coreapi.events.OrderPlacedEvent;

public class OrderAggregateUnitTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    public void giveNoPriorActivity_whenPlaceOrderCommand_thenShouldPublishOrderPlacedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.givenNoPriorActivity()
               .when(new PlaceOrderCommand(orderId, product))
               .expectEvents(new OrderPlacedEvent(orderId, product));
    }

}
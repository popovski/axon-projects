package com.baeldung.axon.commandmodel;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.*;

import mk.code.factory.axon.commandmodel.OrderAggregate;
import mk.code.factory.axon.coreapi.commands.ConfirmOrderCommand;
import mk.code.factory.axon.coreapi.commands.PlaceOrderCommand;
import mk.code.factory.axon.coreapi.commands.ShipOrderCommand;
import mk.code.factory.axon.coreapi.events.OrderConfirmedEvent;
import mk.code.factory.axon.coreapi.events.OrderPlacedEvent;
import mk.code.factory.axon.coreapi.events.OrderShippedEvent;

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

    @Test
    public void givenOrderPlacedEvent_whenConfirmOrderCommand_thenShouldPublishOrderConfirmedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
               .when(new ConfirmOrderCommand(orderId))
               .expectEvents(new OrderConfirmedEvent(orderId));
    }

    @Test
    public void givenOrderPlacedEvent_whenShipOrderCommand_thenShouldThrowIllegalStateException() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
               .when(new ShipOrderCommand(orderId))
               .expectException(IllegalStateException.class);
    }

    @Test
    public void givenOrderPlacedEventAndOrderConfirmedEvent_whenShipOrderCommand_thenShouldPublishOrderShippedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
               .when(new ShipOrderCommand(orderId))
               .expectEvents(new OrderShippedEvent(orderId));
    }

}
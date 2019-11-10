package mk.code.factory.axon.querymodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import mk.code.factory.axon.coreapi.events.OrderConfirmedEvent;
import mk.code.factory.axon.coreapi.events.OrderPlacedEvent;
import mk.code.factory.axon.coreapi.events.OrderShippedEvent;
import mk.code.factory.axon.coreapi.queries.FindAllOrderedProductsQuery;
import mk.code.factory.axon.coreapi.queries.OrderedProduct;

@Service
public class OrderedProductsEventHandler {

    private final Map<String, OrderedProduct> orderedProducts = new HashMap<>();

    @EventHandler
    public void on(OrderPlacedEvent event) {
        String orderId = event.getOrderId();
        orderedProducts.put(orderId, new OrderedProduct(orderId, event.getProduct()));
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        orderedProducts.computeIfPresent(event.getOrderId(), (orderId, orderedProduct) -> {
            orderedProduct.setOrderConfirmed();
            return orderedProduct;
        });
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        orderedProducts.computeIfPresent(event.getOrderId(), (orderId, orderedProduct) -> {
            orderedProduct.setOrderShipped();
            return orderedProduct;
        });
    }

    @QueryHandler
    public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orderedProducts.values());
    }

}
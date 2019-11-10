package mk.factory.code.axon.querymodel;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mk.factory.code.axon.coreapi.events.OrderPlacedEvent;
import mk.factory.code.book.domain.Book;
import mk.factory.code.repository.BookRepository;

@Service
public class OrderedProductsEventHandler {

	@Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(OrderPlacedEvent event) {
        String orderId = event.getOrderId();
        // create book entity
        // save using the repository
        Book book = new Book();
        book.setIsbn(event.getOrderId());
        
        bookRepository.save(book);
    }

}
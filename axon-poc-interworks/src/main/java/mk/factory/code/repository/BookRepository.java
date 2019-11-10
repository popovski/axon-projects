package mk.factory.code.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mk.factory.code.book.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
	List<Book> findByIsbn(Integer isbn);
}

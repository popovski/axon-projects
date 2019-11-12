package mk.factory.code.book.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mk.factory.code.book.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
	List<Book> findById(Integer id);
	List<Book> findAll();
}

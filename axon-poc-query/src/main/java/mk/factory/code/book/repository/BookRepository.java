package mk.factory.code.book.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mk.factory.code.book.domain.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {
	BookEntity findByGuid(String id);
	List<BookEntity> findAll();
}

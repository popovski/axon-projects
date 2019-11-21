package mk.factory.code.book.status.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mk.factory.code.book.status.domain.BookStatus;

@Repository
public interface BookStatusRepository extends CrudRepository<BookStatus, String> {
	List<BookStatus> findById(Integer id);
	List<BookStatus> findAll();
}

package mk.factory.code.book.status.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mk.factory.code.book.status.domain.BookStatusEntity;

@Repository
public interface BookStatusRepository extends CrudRepository<BookStatusEntity, String> {
	BookStatusEntity findByGuid(String id);
	List<BookStatusEntity> findAll();
}

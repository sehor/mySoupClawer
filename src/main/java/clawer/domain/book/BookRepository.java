package clawer.domain.book;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String>, BookDataHelper {

	List<Book> findByWebSiteName(String webSiteName);
	List<Book> findByUrl(String url);
}


package clawer.domain.chapter;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends MongoRepository<Chapter, String>, ChapterDataHelper {
 
	List<Chapter> findByBookName(String bookName);
}


package clawer.domain.urlTree;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlTreeRepository extends MongoRepository<UrlTree, String>, UrlTreeDataHelper {

}


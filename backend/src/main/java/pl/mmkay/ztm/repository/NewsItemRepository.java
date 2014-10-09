package pl.mmkay.ztm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mmkay.ztm.model.NewsItem;

public interface NewsItemRepository extends MongoRepository<NewsItem, ObjectId> {
    
}

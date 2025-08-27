package vn.couple_app.file_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.couple_app.file_service.entity.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
}

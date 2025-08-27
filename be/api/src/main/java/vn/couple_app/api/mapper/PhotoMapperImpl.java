package vn.couple_app.api.mapper;

import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.couple_app.api.dto.response.PhotoResponse;
import vn.couple_app.api.entity.Photo;

@Component
public class PhotoMapperImpl implements PhotoMapper {
    @Value("${services.api.file-service.base-url}")
    String fileServiceBaseUrl;

    @Override
    public PhotoResponse toPhotoResponse(Photo photo) {
        if (photo == null) return null;
        return PhotoResponse.builder().url(fileServiceBaseUrl + photo.getUrl()).build();
    }
}
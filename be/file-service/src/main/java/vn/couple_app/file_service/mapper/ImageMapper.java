package vn.couple_app.file_service.mapper;

import org.springframework.stereotype.Component;
import vn.couple_app.file_service.dto.response.SaveImageResponse;
import vn.couple_app.file_service.entity.Image;

@Component
public class ImageMapper {
    public SaveImageResponse toImageResponse(Image image) {
        if (image == null)
            return null;
        return SaveImageResponse
            .builder()
            .id(image.getId())
            .url(image.getUrl())
            .build();
    }
}

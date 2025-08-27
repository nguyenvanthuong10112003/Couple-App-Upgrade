package vn.couple_app.api.mapper;

import vn.couple_app.api.dto.response.PhotoResponse;
import vn.couple_app.api.entity.Photo;

public interface PhotoMapper {
    PhotoResponse toPhotoResponse(Photo photo);
}

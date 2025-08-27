package vn.couple_app.api.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.couple_app.api.dto.request.DeleteImageRequest;
import vn.couple_app.api.dto.request.SaveImageRequest;
import vn.couple_app.api.dto.response.ResponseApi;
import vn.couple_app.api.dto.response.SaveImageResponse;
import vn.couple_app.api.entity.Photo;
import vn.couple_app.api.repository.httpclient.FileServiceClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoService extends BaseService {
    final FileServiceClient fileServiceClient;
    final RabbitMQProducer rabbitMQProducer;
    public Photo savePhoto(Photo oldPhoto, MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty())
            return oldPhoto;
        String userId = getUserId();
        ResponseApi<SaveImageResponse> res = null;
        try {
            res = fileServiceClient
                .uploadImage(SaveImageRequest.builder().file(imageFile).build());
            SaveImageResponse newImage = res.getData();
            if (oldPhoto == null)
                oldPhoto = new Photo();
            else
                rabbitMQProducer
                    .delImage(createDeleteImageRequest(userId, oldPhoto));
            mapPhoto(oldPhoto, newImage);
        } catch (FeignException | NullPointerException exception) {
            log.error("Exception: {}", exception.getMessage());
            log.error("Response: {}", res);
        }
        return oldPhoto;
    }

    private DeleteImageRequest createDeleteImageRequest(String userId, Photo photo) {
        return DeleteImageRequest
            .builder()
            .id(photo.getPhotoKey())
            .userId(userId)
            .build();
    }

    private void mapPhoto(Photo photo, SaveImageResponse response) {
        photo.setPhotoKey(response.getId());
        photo.setUrl(response.getUrl());
    }
}

package vn.couple_app.file_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.couple_app.file_service.dto.request.DeleteImageRequest;
import vn.couple_app.file_service.dto.request.SaveImageRequest;
import vn.couple_app.file_service.dto.response.SaveImageResponse;
import vn.couple_app.file_service.entity.Image;
import vn.couple_app.file_service.exception.AppException;
import vn.couple_app.file_service.exception.ErrorCode;
import vn.couple_app.file_service.mapper.ImageMapper;
import vn.couple_app.file_service.repository.ImageRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    final String urlController = "/images";
    final ImageRepository imageRepository;
    final ImageMapper imageMapper;
    public SaveImageResponse saveImage(SaveImageRequest imageRequest) {
        MultipartFile file = imageRequest.getFile();
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        String id = UUID.randomUUID().toString();
        String url = urlController + "/" + id;
        try {
            Image image = Image.builder()
                .id(id)
                .url(url)
                .content(file.getBytes())
                .userId(userId)
                .contentType(file.getContentType())
                .build();

            Image saved = imageRepository.save(image);

            return imageMapper.toImageResponse(saved);

        } catch (Exception e) {
            throw new RuntimeException("Không thể lưu file: " + e.getMessage(), e);
        }
    }

    public Image getById(String id) {
        return imageRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));
    }

    public void delete(DeleteImageRequest request) {
        var image = imageRepository.findById(request.getId())
           .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));

       if (!image.getUserId().equals(request.getUserId()))
           throw new AppException(ErrorCode.UNAUTHORIZED);

       imageRepository.delete(image);
    }
}

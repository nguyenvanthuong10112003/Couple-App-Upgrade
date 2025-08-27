package vn.couple_app.file_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.couple_app.file_service.dto.request.DeleteImageRequest;
import vn.couple_app.file_service.dto.request.SaveImageRequest;
import vn.couple_app.file_service.dto.response.SaveImageResponse;
import vn.couple_app.file_service.dto.response.ResponseApi;
import vn.couple_app.file_service.entity.Image;
import vn.couple_app.file_service.exception.AppException;
import vn.couple_app.file_service.exception.ErrorCode;
import vn.couple_app.file_service.service.ImageService;

import java.util.Arrays;


@Slf4j
@RestController
@RequestMapping("/images")
public class ImageController {
    final String[] allowedUri = {
        "http://localhost:8001/"
    };
    final ImageService imageService;
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @PostMapping("/upload")
    public ResponseApi<SaveImageResponse> saveImage(HttpServletRequest request, SaveImageRequest imageRequest) {
        checkRef(request);
        return ResponseApi.createSuccess(imageService.saveImage(imageRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String id) {
        Image image = imageService.getById(id);

        ByteArrayResource resource = new ByteArrayResource(image.getContent());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"image-" + id + "\"")
            .contentType(MediaType.parseMediaType(image.getContentType()))
            .body(resource);
    }
    private void checkRef(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        log.info("Request uri: {}", requestUrl);
        //if (!Strings.isEmpty() &&
        //    Arrays.stream(allowedUri).noneMatch(referer::contains))
        //    throw new AppException(ErrorCode.UNAUTHORIZED);
    }
}

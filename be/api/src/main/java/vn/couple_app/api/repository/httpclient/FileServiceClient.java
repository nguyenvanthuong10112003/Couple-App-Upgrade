package vn.couple_app.api.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import vn.couple_app.api.config.AuthenticationRequestInterceptor;
import vn.couple_app.api.dto.request.DeleteImageRequest;
import vn.couple_app.api.dto.request.SaveImageRequest;
import vn.couple_app.api.dto.response.ResponseApi;
import vn.couple_app.api.dto.response.SaveImageResponse;

@FeignClient(name = "${services.api.file-service.name}",
    url = "${services.api.file-service.base-url}",
    configuration = { AuthenticationRequestInterceptor.class })
public interface FileServiceClient {
    @PostMapping(value = "${services.api.file-service.controllers.save-image}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseApi<SaveImageResponse> uploadImage(SaveImageRequest request);
}

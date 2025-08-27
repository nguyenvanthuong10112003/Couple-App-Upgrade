package vn.couple_app.file_service.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.couple_app.file_service.dto.request.IntrospectRequest;
import vn.couple_app.file_service.dto.response.IntrospectResponse;
import vn.couple_app.file_service.dto.response.ResponseApi;

@FeignClient(name = "identity-service", url = "http://localhost:8001/api/v1")
public interface IdentityClient {
    @PostMapping(value = "/auth/introspect", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseApi<IntrospectResponse> introspect(@RequestBody IntrospectRequest request);
}

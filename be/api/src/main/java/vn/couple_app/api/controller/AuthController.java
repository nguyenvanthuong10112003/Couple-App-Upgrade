package vn.couple_app.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.couple_app.api.dto.request.IntrospectRequest;
import vn.couple_app.api.dto.request.LoginRequest;
import vn.couple_app.api.dto.request.RegisterRequest;
import vn.couple_app.api.dto.response.IntrospectResponse;
import vn.couple_app.api.dto.response.LoginResponse;
import vn.couple_app.api.dto.response.RegisterResponse;
import vn.couple_app.api.dto.response.ResponseApi;
import vn.couple_app.api.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    final AuthService authService;
    @PostMapping("/register")
    public ResponseApi<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseApi
            .createSuccess(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseApi<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseApi
            .createSuccess(authService.login(request));
    }
    @PostMapping("/introspect")
    public ResponseApi<IntrospectResponse> introspect(@RequestBody @Valid IntrospectRequest request) {
        return ResponseApi
            .createSuccess(authService.introspect(request));
    }
}

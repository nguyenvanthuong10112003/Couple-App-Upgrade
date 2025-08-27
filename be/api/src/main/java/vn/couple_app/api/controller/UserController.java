package vn.couple_app.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.couple_app.api.dto.request.UserUpdateRequest;
import vn.couple_app.api.dto.response.ResponseApi;
import vn.couple_app.api.dto.response.UserResponse;
import vn.couple_app.api.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;
    @GetMapping("/mind")
    public ResponseApi<UserResponse> mind() {
        return ResponseApi
            .createSuccess(userService.mind());
    }
    @PostMapping("/update")
    public ResponseApi<UserResponse> update(@Valid UserUpdateRequest requestData) {
        return ResponseApi
            .createSuccess(userService.update(requestData));
    }
}

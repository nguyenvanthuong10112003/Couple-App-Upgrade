package vn.couple_app.api.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.couple_app.api.dto.request.DeleteImageRequest;
import vn.couple_app.api.dto.request.SaveImageRequest;
import vn.couple_app.api.dto.request.SendEmailDto;
import vn.couple_app.api.dto.request.UserUpdateRequest;
import vn.couple_app.api.dto.response.ResponseApi;
import vn.couple_app.api.dto.response.SaveImageResponse;
import vn.couple_app.api.dto.response.UserResponse;
import vn.couple_app.api.entity.Photo;
import vn.couple_app.api.entity.User;
import vn.couple_app.api.exception.AppException;
import vn.couple_app.api.exception.ErrorCode;
import vn.couple_app.api.mapper.UserMapper;
import vn.couple_app.api.repository.UserRepository;
import vn.couple_app.api.repository.httpclient.FileServiceClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends BaseService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final RabbitMQProducer rabbitMQProducer;
    final PhotoService photoService;
    final EmailService emailService;

    public UserResponse mind() {
        String userId = getUserId();
        return userMapper.toUserResponse(userRepository
            .findById(userId)
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse update(UserUpdateRequest request) {
        String userId = getUserId();
        var oldUser = userRepository.findById(userId)
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.toUpdate(oldUser, request);
        Photo newPhoto = photoService.savePhoto(oldUser.getAvatar(), request.getAvatarFile());
        oldUser.setAvatar(newPhoto);
        if (!Objects.requireNonNullElse(oldUser.getFirstUpdated(), false)) {
            oldUser.setFirstUpdated(true);
            emailService.welcomeUser(oldUser);
        }
        oldUser = userRepository.save(oldUser);
        return userMapper.toUserResponse(oldUser);
    }
}

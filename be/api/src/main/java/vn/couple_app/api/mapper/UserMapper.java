package vn.couple_app.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.couple_app.api.dto.request.UserUpdateRequest;
import vn.couple_app.api.dto.response.UserResponse;
import vn.couple_app.api.entity.User;

@Mapper(componentModel = "spring",
        uses = {PhotoMapper.class})
public interface UserMapper {
    @Mapping(target = "email", source = "user.account.email")
    UserResponse toUserResponse(User user);

    void toUpdate(@MappingTarget User user, UserUpdateRequest request);
}

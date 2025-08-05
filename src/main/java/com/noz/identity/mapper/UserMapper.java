package com.noz.identity.mapper;

import com.noz.identity.dto.request.UserCreationRequest;
import com.noz.identity.dto.request.UserUpdateRequest;
import com.noz.identity.dto.response.UserResponse;
import com.noz.identity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(source = "firstname",target = "lastname")//firstname va lastname trung nhau
//    @Mapping(target = "lastname",ignore = true)//lastname tra ve gia tri null
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

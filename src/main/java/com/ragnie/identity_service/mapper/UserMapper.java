package com.ragnie.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ragnie.identity_service.dto.request.UserCreationRequest;
import com.ragnie.identity_service.dto.request.UserUpdateRequest;
import com.ragnie.identity_service.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

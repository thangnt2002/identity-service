package com.thangnt.identity_service.mapper;

import com.thangnt.identity_service.dto.request.UserCreationRequest;
import com.thangnt.identity_service.dto.request.UserUpdateRequest;
import com.thangnt.identity_service.dto.response.UserCreationResponse;
import com.thangnt.identity_service.dto.response.UserSearchResponse;
import com.thangnt.identity_service.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)

    User toUser(UserCreationRequest userCreationRequest);
    UserCreationResponse toUserResponse(User user);
    UserSearchResponse toUserSearchResponse(User user);
    @Mapping(target = "roles", ignore = true)
    User toUser(UserUpdateRequest userUpdateRequest);
}

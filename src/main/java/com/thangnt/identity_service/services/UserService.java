package com.thangnt.identity_service.services;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.UserCreationRequest;
import com.thangnt.identity_service.dto.request.UserUpdateRequest;
import com.thangnt.identity_service.dto.response.UserCreationResponse;
import com.thangnt.identity_service.dto.response.UserSearchResponse;

public interface UserService {
    ApiResponse<UserCreationResponse> create(UserCreationRequest userCreationRequest);
    ApiResponse<UserSearchResponse> findById(String id);
    ApiResponse<UserCreationResponse> update(UserUpdateRequest userUpdateRequest);
    ApiResponse<UserCreationResponse> getAll();


}

package com.thangnt.identity_service.services;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.PermissionCreationRequest;
import com.thangnt.identity_service.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    ApiResponse<PermissionResponse> create(PermissionCreationRequest request);
    ApiResponse<List<PermissionResponse>> getAll();
    ApiResponse<?> delete(String id);
}

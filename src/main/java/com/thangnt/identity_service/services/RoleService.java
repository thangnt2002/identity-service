package com.thangnt.identity_service.services;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.PermissionCreationRequest;
import com.thangnt.identity_service.dto.request.RoleCreationRequest;
import com.thangnt.identity_service.dto.response.PermissionResponse;
import com.thangnt.identity_service.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    ApiResponse<RoleResponse> create(RoleCreationRequest request);
    ApiResponse<List<RoleResponse>> getAll();
    ApiResponse<?> delete(String id);
}

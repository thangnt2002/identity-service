package com.thangnt.identity_service.services.impl;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.PermissionCreationRequest;
import com.thangnt.identity_service.dto.response.PermissionResponse;
import com.thangnt.identity_service.entities.Permission;
import com.thangnt.identity_service.mapper.PermissionMapper;
import com.thangnt.identity_service.repositories.PermissionRepository;
import com.thangnt.identity_service.services.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public ApiResponse<PermissionResponse> create(PermissionCreationRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return ApiResponse.<PermissionResponse>builder()
                .success(true)
                .code(201)
                .data(permissionMapper.toPermissionResponse(permission))
                .build();
    }

    @Override
    public ApiResponse<List<PermissionResponse>> getAll() {
         List<PermissionResponse> responses =
                 permissionRepository.findAll()
                         .stream()
                         .map(permissionMapper::toPermissionResponse).toList();
        return ApiResponse.<List<PermissionResponse>>builder()
                .success(true)
                .code(200)
                .data(responses)
                .build();
    }

    @Override
    public ApiResponse<?> delete(String id) {
//        Permission permission = permissionRepository.findById(id).get();
//        if(permission == null){
//         throw new NotFoundException(E)
//        }
        permissionRepository.deleteById(id);
        return ApiResponse.builder()
                .success(true)
                .code(200)
                .build();
    }
}

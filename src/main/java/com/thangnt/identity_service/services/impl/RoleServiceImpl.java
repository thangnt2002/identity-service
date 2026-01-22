package com.thangnt.identity_service.services.impl;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.RoleCreationRequest;
import com.thangnt.identity_service.dto.response.RoleResponse;
import com.thangnt.identity_service.entities.Role;
import com.thangnt.identity_service.mapper.RoleMapper;
import com.thangnt.identity_service.repositories.PermissionRepository;
import com.thangnt.identity_service.repositories.RoleRepository;
import com.thangnt.identity_service.services.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    RoleMapper roleMapper;

    @Override
    public ApiResponse<RoleResponse> create(RoleCreationRequest request) {
        Role role = roleMapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        role = roleRepository.save(role);
        return ApiResponse.<RoleResponse>builder()
                .success(true)
                .code(201)
                .data(roleMapper.toRoleResponse(role))
                .build();
    }

    @Override
    public ApiResponse<List<RoleResponse>> getAll() {
         List<RoleResponse> responses =
                 roleRepository.findAll()
                         .stream()
                         .map(roleMapper::toRoleResponse).toList();
        return ApiResponse.<List<RoleResponse>>builder()
                .success(true)
                .code(200)
                .data(responses)
                .build();
    }

    @Override
    public ApiResponse<?> delete(String id) {
        roleRepository.deleteById(id);
        return ApiResponse.builder()
                .success(true)
                .code(200)
                .build();
    }
}

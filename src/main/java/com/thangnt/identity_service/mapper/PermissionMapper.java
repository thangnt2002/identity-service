package com.thangnt.identity_service.mapper;

import com.thangnt.identity_service.dto.request.PermissionCreationRequest;
import com.thangnt.identity_service.dto.response.PermissionResponse;
import com.thangnt.identity_service.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}

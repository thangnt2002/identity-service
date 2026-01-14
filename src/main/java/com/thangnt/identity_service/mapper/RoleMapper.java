package com.thangnt.identity_service.mapper;

import com.thangnt.identity_service.dto.request.RoleCreationRequest;
import com.thangnt.identity_service.dto.response.RoleResponse;
import com.thangnt.identity_service.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreationRequest request);
    RoleResponse toRoleResponse(Role role);
}

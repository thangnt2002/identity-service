package com.thangnt.identity_service.dto.response;

import com.thangnt.identity_service.entities.Permission;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse
{
    String name;
    String description;
    Set<Permission> permissions;
}

package com.thangnt.identity_service.dto.request;

import com.thangnt.identity_service.entities.Permission;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreationRequest
{
    String name;
    String description;
    Set<String> permissions;
}

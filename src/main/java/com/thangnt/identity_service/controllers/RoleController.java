package com.thangnt.identity_service.controllers;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.RoleCreationRequest;
import com.thangnt.identity_service.dto.response.RoleResponse;
import com.thangnt.identity_service.services.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

    @PostMapping
    private ApiResponse<RoleResponse> create(@RequestBody RoleCreationRequest request){
        return roleService.create(request);
    }

    @GetMapping
    private ApiResponse<List<RoleResponse>> getAll(){
        return roleService.getAll();
    }

    @DeleteMapping("/{id}")
    private ApiResponse<?> delete(@PathVariable String id){
        return roleService.delete(id);
    }

}

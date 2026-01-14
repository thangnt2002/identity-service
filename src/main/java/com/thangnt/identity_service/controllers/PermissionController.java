package com.thangnt.identity_service.controllers;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.PermissionCreationRequest;
import com.thangnt.identity_service.dto.response.PermissionResponse;
import com.thangnt.identity_service.services.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    private ApiResponse<PermissionResponse> create(@RequestBody PermissionCreationRequest request){
        return permissionService.create(request);
    }

    @GetMapping
    private ApiResponse<List<PermissionResponse>> getAll(){
        return permissionService.getAll();
    }

    @DeleteMapping("/{id}")
    private ApiResponse<?> delete(@PathVariable String id){
        return permissionService.delete(id);
    }

}

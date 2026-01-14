package com.thangnt.identity_service.controllers;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.UserCreationRequest;
import com.thangnt.identity_service.dto.request.UserUpdateRequest;
import com.thangnt.identity_service.dto.response.UserCreationResponse;
import com.thangnt.identity_service.dto.response.UserSearchResponse;
import com.thangnt.identity_service.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    private ApiResponse<UserCreationResponse> create(@RequestBody @Valid UserCreationRequest request){
        return userService.create(request);
    }

    @PostMapping("/{id}")
    private ApiResponse<UserSearchResponse> create(@PathVariable String id){
        return userService.findById(id);
    }

    @PutMapping
    private ApiResponse<UserCreationResponse> update(@RequestBody UserUpdateRequest request){
        return userService.update(request);
    }

}

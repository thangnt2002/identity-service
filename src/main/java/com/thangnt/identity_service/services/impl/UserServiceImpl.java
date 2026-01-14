package com.thangnt.identity_service.services.impl;

import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.UserCreationRequest;
import com.thangnt.identity_service.dto.request.UserUpdateRequest;
import com.thangnt.identity_service.dto.response.UserCreationResponse;
import com.thangnt.identity_service.dto.response.UserSearchResponse;
import com.thangnt.identity_service.entities.User;
import com.thangnt.identity_service.enums.Role;
import com.thangnt.identity_service.exception.NotFoundException;
import com.thangnt.identity_service.mapper.UserMapper;
import com.thangnt.identity_service.repositories.RoleRepository;
import com.thangnt.identity_service.repositories.UserRepository;
import com.thangnt.identity_service.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

     UserRepository userRepository;
     UserMapper userMapper;
     PasswordEncoder passwordEncoder;
     RoleRepository roleRepository;

    @Override
    public ApiResponse<UserCreationResponse> create(UserCreationRequest userCreationRequest) {
        User user = userMapper.toUser(userCreationRequest);
        UserCreationResponse response = userMapper.toUserResponse(user);

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
//        user.setRoles(roles);

        return ApiResponse.<UserCreationResponse>builder()
                .success(true).code(201)
                .data(response)
                .build();
    }

    @Override
    public ApiResponse<UserSearchResponse> findById(String id) {
        User user = userRepository.findById(id).get();
        UserSearchResponse response = userMapper.toUserSearchResponse(user);

        return ApiResponse.<UserSearchResponse>builder()
                .success(true).code(201)
                .data(response)
                .build();
    }

    @Override
    public ApiResponse<UserCreationResponse> update(UserUpdateRequest userUpdateRequest) {
        boolean isUserExisted = userRepository.existsById(userUpdateRequest.getId());
        if(!isUserExisted){
            throw new NotFoundException(404);
        }
        User user = userMapper.toUser(userUpdateRequest);
        List<com.thangnt.identity_service.entities.Role> roles = roleRepository.findAllById(userUpdateRequest.getRoles());

        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
        UserCreationResponse response = userMapper.toUserResponse(user);

        return ApiResponse.<UserCreationResponse>builder()
                .success(true).code(201)
                .data(response)
                .build();
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAllAuthorities('READ_ALL_USER')")
    @Override
    public ApiResponse<UserCreationResponse> getAll() {
        return null;
    }
}

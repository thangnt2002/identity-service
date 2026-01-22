package com.thangnt.identity_service.services.impl;

import com.thangnt.identity_service.common.PredefinedRole;
import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.UserCreationRequest;
import com.thangnt.identity_service.dto.request.UserUpdateRequest;
import com.thangnt.identity_service.dto.response.UserCreationResponse;
import com.thangnt.identity_service.dto.response.UserSearchResponse;
import com.thangnt.identity_service.entities.Role;
import com.thangnt.identity_service.entities.User;
import com.thangnt.identity_service.exception.BusinessException;
import com.thangnt.identity_service.exception.ErrorCode;
import com.thangnt.identity_service.exception.NotFoundException;
import com.thangnt.identity_service.mapper.UserMapper;
import com.thangnt.identity_service.repositories.RoleRepository;
import com.thangnt.identity_service.repositories.UserRepository;
import com.thangnt.identity_service.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
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
        Set<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
        UserCreationResponse response = userMapper.toUserResponse(user);
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        user.setRoles(roles);
        try{
            userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new BusinessException(ErrorCode.USER_EXISTED.getCode());
        }

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
        List<Role> roles = roleRepository.findAllById(userUpdateRequest.getRoles());

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

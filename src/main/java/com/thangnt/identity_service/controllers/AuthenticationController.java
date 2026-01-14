package com.thangnt.identity_service.controllers;

import com.nimbusds.jose.JOSEException;
import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.AuthenticationRequest;
import com.thangnt.identity_service.dto.request.IntrospectTokenRequest;
import com.thangnt.identity_service.dto.request.LogoutRequest;
import com.thangnt.identity_service.dto.request.RefreshRequest;
import com.thangnt.identity_service.dto.response.AuthenticationResponse;
import com.thangnt.identity_service.dto.response.IntrospectTokenResponse;
import com.thangnt.identity_service.services.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    private ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }

    @PostMapping("/introspect")
    private ApiResponse<IntrospectTokenResponse> introspect(@RequestBody IntrospectTokenRequest request)
            throws ParseException, JOSEException {
        return authenticationService.introspectToken(request);
    }

    @PostMapping("/logout")
    private ApiResponse<?> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        return authenticationService.logout(request);
    }

    @PostMapping("/refresh")
    private ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        return authenticationService.refresh(request);
    }

}

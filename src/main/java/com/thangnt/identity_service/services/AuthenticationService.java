package com.thangnt.identity_service.services;

import com.nimbusds.jose.JOSEException;
import com.thangnt.identity_service.dto.ApiResponse;
import com.thangnt.identity_service.dto.request.AuthenticationRequest;
import com.thangnt.identity_service.dto.request.IntrospectTokenRequest;
import com.thangnt.identity_service.dto.request.LogoutRequest;
import com.thangnt.identity_service.dto.request.RefreshRequest;
import com.thangnt.identity_service.dto.response.AuthenticationResponse;
import com.thangnt.identity_service.dto.response.IntrospectTokenResponse;

import java.text.ParseException;

public interface AuthenticationService {
    ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request);
    ApiResponse<IntrospectTokenResponse> introspectToken(IntrospectTokenRequest request)  throws JOSEException, ParseException;
    ApiResponse<?> logout(LogoutRequest request)  throws JOSEException, ParseException;

    ApiResponse<AuthenticationResponse> refresh(RefreshRequest request) throws ParseException, JOSEException;

}

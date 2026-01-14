package com.thangnt.identity_service.services;

public interface InvalidateJWTService {
    public void logout(String token);
}

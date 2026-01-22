package com.thangnt.identity_service.dto.request;

import com.thangnt.identity_service.validator.DobConstraint;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserUpdateRequest {
    String id;
    String password;
    String firstName;
    String lastName;
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
    Set<String> roles;
}

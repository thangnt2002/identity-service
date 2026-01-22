package com.thangnt.identity_service.dto.request;

import com.thangnt.identity_service.validator.DobConstraint;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserCreationRequest {
    String userName;
    String password;
    String firstName;
    String lastName;
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
}

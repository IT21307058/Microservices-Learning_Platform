package com.ds.edustack.auth.service.auth;

import com.ds.edustack.auth.dto.SignupRequest;
import com.ds.edustack.auth.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}

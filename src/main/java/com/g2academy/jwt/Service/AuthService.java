package com.g2academy.jwt.Service;

import com.g2academy.jwt.Dto.RequestResponse;

public interface AuthService {
    RequestResponse signUp(RequestResponse registrationRequest);
    RequestResponse signIn(RequestResponse signinRequest);
    RequestResponse refreshToken(RequestResponse signinRequest);
}

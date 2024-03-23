package com.g2academy.jwt.Controller;

import com.g2academy.jwt.Dto.RequestResponse;
import com.g2academy.jwt.Service.Implement.AuthServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImplement authServiceImplement;

    @PostMapping("signup")
    public ResponseEntity<RequestResponse> Signup(@RequestBody RequestResponse signupRequest){
        return ResponseEntity.ok(authServiceImplement.signUp(signupRequest));
    }

    @PostMapping("signin")
    public ResponseEntity<RequestResponse> SignIn(@RequestBody RequestResponse signinRequest){
        return ResponseEntity.ok(authServiceImplement.signIn(signinRequest));
    }

    @PostMapping("refresh")
    public ResponseEntity<RequestResponse> RefreshToken(@RequestBody RequestResponse refreshTokenRequest){
        return ResponseEntity.ok(authServiceImplement.refreshToken(refreshTokenRequest));
    }


}

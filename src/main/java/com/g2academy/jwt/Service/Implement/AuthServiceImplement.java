package com.g2academy.jwt.Service.Implement;

import com.g2academy.jwt.Dto.RequestResponse;
import com.g2academy.jwt.Entity.Users;
import com.g2academy.jwt.Repository.UsersRepository;
import com.g2academy.jwt.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImplement implements AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtilImplement jwtUtilImplement;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public RequestResponse signUp(RequestResponse registrationRequest) {
        RequestResponse resp = new RequestResponse();
        try {
            Users ourUsers = new Users();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            Users ourUserResult = usersRepository.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId()>0) {
                resp.setUsers(ourUserResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }


    @Override
    public RequestResponse signIn(RequestResponse signinRequest) {
        RequestResponse response = new RequestResponse();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
            var user = usersRepository.findByEmail(signinRequest.getEmail()).orElseThrow();
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtilImplement.generateToken(user);
            var refreshToken = jwtUtilImplement.generateRefrashToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }


    @Override
    public RequestResponse refreshToken(RequestResponse refreshTokenReqiest) {
        RequestResponse response = new RequestResponse();
        String ourEmail = jwtUtilImplement.extractUsername(refreshTokenReqiest.getToken());
        Users users = usersRepository.findByEmail(ourEmail).orElseThrow();
        if (jwtUtilImplement.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtilImplement.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}

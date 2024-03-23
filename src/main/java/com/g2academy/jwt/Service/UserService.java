package com.g2academy.jwt.Service;

import com.g2academy.jwt.Entity.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    Users saveUser(Users user);
}

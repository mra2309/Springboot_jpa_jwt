package com.g2academy.jwt.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class WelcomeController {

    @GetMapping("welcome")
    public String welcome(){
        return "selamat datang!";
    }

}

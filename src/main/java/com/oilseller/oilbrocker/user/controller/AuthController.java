package com.oilseller.oilbrocker.user.controller;

import com.oilseller.oilbrocker.user.dto.LoginResponse;
import com.oilseller.oilbrocker.user.param.LoginParam;
import com.oilseller.oilbrocker.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginParam loginParam) {
        return authService.login(loginParam.getUsername(), loginParam.getPassword());
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout() {
        authService.logout();
    }
}

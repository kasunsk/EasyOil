package com.oilseller.oilbrocker.user.controller;

import com.oilseller.oilbrocker.user.adaptor.UserParamAdaptor;
import com.oilseller.oilbrocker.user.param.UserParam;
import com.oilseller.oilbrocker.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private UserParamAdaptor userParamAdaptor = new UserParamAdaptor();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long addUser(@RequestBody UserParam userParam) {
        return userService.addUser(userParamAdaptor.fromParam(userParam));
    }
}

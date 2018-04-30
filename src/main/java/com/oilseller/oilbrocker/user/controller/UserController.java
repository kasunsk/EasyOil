package com.oilseller.oilbrocker.user.controller;

import com.oilseller.oilbrocker.user.adaptor.UserParamAdaptor;
import com.oilseller.oilbrocker.user.dto.UserRoleType;
import com.oilseller.oilbrocker.user.param.UserParam;
import com.oilseller.oilbrocker.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private UserParamAdaptor userParamAdaptor = new UserParamAdaptor();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Long addUser(@RequestBody UserParam userParam) {
        return userService.addUser(userParamAdaptor.fromParam(userParam));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public List<UserParam> loadAllUsers() {
        return userParamAdaptor.fromDtoList(userService.loadAllUsers());
    }

    @CrossOrigin
    @RequestMapping(value = "/availableRoles", method = RequestMethod.GET)
    public List<UserRoleType> loadAvailableRoles() {
        return Arrays.asList(UserRoleType.values());
    }
}

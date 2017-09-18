package ua.shield.jsf.controller;

import ua.shield.entity.User;
import ua.shield.service.SecurityServiceImpl;
import ua.shield.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class UserJsfController {

    User user;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{userService}")
    UserService service;
}

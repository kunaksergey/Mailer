package ua.shield.jsf.controller;

import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
public class MainJsfController<T> {

    private SecurityServiceImpl securityService;
    private IService<T> service;

    public Set<T> EntitySet() {
        return service.findAllByOwner();
    }
}

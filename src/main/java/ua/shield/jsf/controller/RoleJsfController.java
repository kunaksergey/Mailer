package ua.shield.jsf.controller;

import ua.shield.entity.Role;
import ua.shield.service.RoleService;
import ua.shield.service.SecurityServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class RoleJsfController {

    Role role;

    @ManagedProperty("#{roleService}")
    RoleService service;

    public Set<Role> mailAddressSet() {
        return service.findAllByOwner();

    }
}

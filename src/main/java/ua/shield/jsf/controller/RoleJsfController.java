package ua.shield.jsf.controller;

import ua.shield.entity.Role;
import ua.shield.service.RoleService;

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
    RoleService entityService;

    public Set<Role> entitySet() {
        return entityService.findAllByOwner();

    }
}

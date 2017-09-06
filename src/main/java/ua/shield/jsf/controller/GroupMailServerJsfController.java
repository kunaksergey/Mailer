package ua.shield.jsf.controller;

import ua.shield.entity.GroupMailServer;
import ua.shield.service.GroupMailServerService;
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
public class GroupMailServerJsfController {

    GroupMailServer gropMailServer;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{groupMailServerService}")
    GroupMailServerService service;

    public Set<GroupMailServer> mailAddressSet() {
        return service.findAllByOwner();
    }
}

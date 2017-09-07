package ua.shield.jsf.controller;

import ua.shield.helper.FrontMessage;
import ua.shield.helper.Url;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
abstract public class MainJsfController<T> {

    private SecurityServiceImpl securityService;
    private IService<T> service;

    public Set<T> EntitySet() {
        return service.findAllByOwner();
    }



    public void create(){
        init();
        Url.redirect(getUrlEdit());
    }


    abstract public String getUrlEdit();
    abstract public String getUrlList();
    abstract public void init();
    abstract public void save();
}

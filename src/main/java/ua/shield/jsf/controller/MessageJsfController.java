package ua.shield.jsf.controller;

import ua.shield.entity.Message;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.Url;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class MessageJsfController extends MainJsfController<Message> {

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{messageService}")
    private IService<Message> entityService;

    @PostConstruct
    public void init() {
        super.init();
    }

    @Override
    Message newInstance() {
        return new Message();
    }

    @Override
    public String getUrlEdit() {
        return Url.MESSAGE_EDIT_URL;
    }

    @Override
    public String getUrlList() {
        return Url.MESSAGE_LIST_URL;
    }

    public String getText() {
        return getEntity().getText();
    }

    public void setText(String text) {
        getEntity().setText(text);
    }

    public String getTitle() {
        return getEntity().getTitle();
    }

    public void setTitle(String title) {
        getEntity().setTitle(title);
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public IService<Message> getEntityService() {
        return entityService;
    }

    public void setEntityService(IService<Message> entityService) {
        this.entityService = entityService;
    }
}

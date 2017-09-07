package ua.shield.jsf.controller;

import ua.shield.entity.Message;
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
    private Message message;
    private Message selectedMessage;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{messageService}")
    IService service;

    @PostConstruct
    public void init() {
        message = new Message();
    }

    public Set<Message> mailAddressSet() {
        return service.findAllByOwner();

    }

    @Override
    public String getUrlEdit() {
        return Url.MESSAGE_EDIT_URL;
    }

    @Override
    public String getUrlList() {
        return Url.MESSAGE_LIST_URL;
    }


    public void edit() {
        message = selectedMessage;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(Url.MESSAGE_EDIT_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        if (message.getId() == null) {
            message.setOwner(securityService.getRegisteredUser());
            service.add(message);
        } else {
            service.update(message);
        }
        message = new Message();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(Url.MESSAGE_LIST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return message.getText();
    }

    public void setText(String text) {
        message.setText(text);
    }

    public String getTitle() {
        return message.getTitle();
    }

    public void setTitle(String title) {
        message.setTitle(title);
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }

    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

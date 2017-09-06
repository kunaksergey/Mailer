package ua.shield.jsf.controller;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ua.shield.entity.MailServer;
import ua.shield.entity.Message;
import ua.shield.helper.URL;
import ua.shield.service.IService;
import ua.shield.service.MailServerService;
import ua.shield.service.SecurityServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@ViewScoped
public class MailServerJsfController {

    private MailServer mailServer;
    private MailServer selectedMailServer;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{mailServerService}")
    IService service;

    @PostConstruct
    public void init() {
        mailServer = new MailServer();
    }

    public Set<MailServer> mailServerSet() {
        return service.findAllByOwner();

 }
    public void edit() {
        mailServer = selectedMailServer;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(URL.MAIL_SERVER_EDIT_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MailServer getMailServer() {
        return mailServer;
    }

    public void setMailServer(MailServer mailServer) {
        this.mailServer = mailServer;
    }

    public MailServer getSelectedMailServer() {
        return selectedMailServer;
    }

    public void setSelectedMailServer(MailServer selectedMailServer) {
        this.selectedMailServer = selectedMailServer;
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
}


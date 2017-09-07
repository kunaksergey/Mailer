package ua.shield.jsf.controller;

import ua.shield.entity.MailAddress;
import ua.shield.entity.MailServer;
import ua.shield.enumer.Protocol;
import ua.shield.helper.Url;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class MailServerJsfController extends MainJsfController<MailServer> {

    private MailServer mailServer;
    private MailServer selectedMailServer;
    private List<Protocol> protocolList;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{mailServerService}")
    IService service;

    @PostConstruct
    public void init() {
        mailServer = new MailServer();
        protocolList=Arrays.asList(Protocol.values());
    }

    public Set<MailServer> mailServerSet() {
        return service.findAllByOwner();

 }
    public void edit() {
        mailServer = selectedMailServer;
        Url.redirect(Url.MAIL_SERVER_EDIT_URL);
    }

    public void save() {
        if (mailServer.getId() == null) {
            mailServer.setOwner(securityService.getRegisteredUser());
            service.add(mailServer);
        } else {
            service.update(mailServer);
        }
        mailServer = new MailServer();
        Url.redirect(Url.MAIL_SERVER_LIST_URL);

    }

    public void create(){
        init();
        Url.redirect(Url.MAIL_SERVER_EDIT_URL);
    }

    @Override
    public String getUrlEdit() {
        return Url.MAIL_SERVER_EDIT_URL;
    }

    @Override
    public String getUrlList() {
        return Url.MAIL_ADDRESS_LIST_URL;
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

    public List<Protocol> getProtocolList() {
        return protocolList;
    }

    public void setProtocolList(List<Protocol> protocolList) {
        this.protocolList = protocolList;
    }
}


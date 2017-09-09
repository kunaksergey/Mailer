package ua.shield.jsf.controller;

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

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class MailServerJsfController extends MainJsfController<MailServer> {

    private List<Protocol> protocolList;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{mailServerService}")
    private IService<MailServer> entityService;

    @PostConstruct
    public void init() {
        super.init();
        protocolList = Arrays.asList(Protocol.values());
    }

    @Override
    MailServer newInstance() {
        return new MailServer();
    }

   @Override
    public String getUrlEdit() {
        return Url.MAIL_SERVER_EDIT_URL;
    }

    @Override
    public String getUrlList() {
        return Url.MAIL_ADDRESS_LIST_URL;
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public IService<MailServer> getEntityService() {
        return entityService;
    }

    public void setEntityService(IService<MailServer> entityService) {
        this.entityService = entityService;
    }

    public List<Protocol> getProtocolList() {
        return protocolList;
    }

    public void setProtocolList(List<Protocol> protocolList) {
        this.protocolList = protocolList;
    }
}


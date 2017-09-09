package ua.shield.jsf.controller;

import ua.shield.entity.MailAddress;
import ua.shield.helper.Url;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class MailAddressJsfController extends MainJsfController<MailAddress> {

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{mailAddressService}")
    private IService<MailAddress> entityService;

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    @Override
    MailAddress newInstance() {
        return new MailAddress();
    }

    @Override
    public String getUrlEdit() {
        return Url.MAIL_ADDRESS_EDIT_URL;
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

    public IService<MailAddress> getEntityService() {
        return entityService;
    }

    public void setEntityService(IService<MailAddress> entityService) {
        this.entityService = entityService;
    }
}

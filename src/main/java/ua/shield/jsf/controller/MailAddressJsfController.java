package ua.shield.jsf.controller;

import ua.shield.entity.MailAddress;
import ua.shield.entity.MailServer;
import ua.shield.entity.Message;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.URL;
import ua.shield.service.IService;
import ua.shield.service.MailAddressService;
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
public class MailAddressJsfController {

    private MailAddress mailAddress;
    private MailAddress selectedMailAddress;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{mailAddressService}")
    IService<MailAddress> service;

    @PostConstruct
    public void init() {
        mailAddress = new MailAddress();
    }

    public Set<MailAddress> mailAddressSet() {
        return service.findAllByOwner();
    }

    public void save() {
        if (mailAddress.getId() == null) {
            mailAddress.setOwner(securityService.getRegisteredUser());
            service.add(mailAddress);
        } else {
            service.update(mailAddress);
        }
        mailAddress = new MailAddress();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(URL.MAIL_ADDRESS_LIST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        mailAddress = selectedMailAddress;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(URL.MAIL_ADDRESS_EDIT_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        service.delete(selectedMailAddress);
        selectedMailAddress=null;
        FrontMessage.addMessage("Mail address was deleted");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(URL.MAIL_ADDRESS_LIST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MailAddress getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(MailAddress mailAddress) {
        this.mailAddress = mailAddress;
    }

    public MailAddress getSelectedMailAddress() {
        return selectedMailAddress;
    }

    public void setSelectedMailAddress(MailAddress selectedMailAddress) {
        this.selectedMailAddress = selectedMailAddress;
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

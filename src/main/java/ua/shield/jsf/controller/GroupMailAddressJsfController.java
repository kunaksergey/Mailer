package ua.shield.jsf.controller;

import org.primefaces.model.DualListModel;
import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.MailAddress;
import ua.shield.helper.Url;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class GroupMailAddressJsfController extends MainGroupJsfController<GroupMailAddress, MailAddress> {

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{groupMailAddressService}")
    private IService<GroupMailAddress> entityService;

    @ManagedProperty("#{mailAddressService}")
    private IService<MailAddress> detailService;

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    @Override
    GroupMailAddress newInstance() {
        return new GroupMailAddress();
    }

    @Override
    public String getUrlEdit() {
        return Url.GROUP_MAIL_ADDRESS_EDIT_URL;
    }

    @Override
    public String getUrlList() {
        return Url.GROUP_MAIL_ADDRESS_LIST_URL;
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public IService<GroupMailAddress> getEntityService() {
        return entityService;
    }

    public void setEntityService(IService<GroupMailAddress> entityService) {
        this.entityService = entityService;
    }

    @Override
    public IService<MailAddress> getDetailService() {
        return detailService;
    }

    public void setDetailService(IService<MailAddress> detailService) {
        this.detailService = detailService;
    }
}

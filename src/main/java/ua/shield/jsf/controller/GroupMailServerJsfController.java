package ua.shield.jsf.controller;

import org.primefaces.model.DualListModel;
import ua.shield.entity.GroupMailServer;
import ua.shield.entity.MailServer;
import ua.shield.helper.Url;
import ua.shield.service.GroupMailServerService;
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
public class GroupMailServerJsfController extends MainGroupJsfController<GroupMailServer, MailServer> {

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{groupMailServerService}")
    private GroupMailServerService entityService;

    @ManagedProperty("#{mailServerService}")
    private IService<MailServer> detailService;

    @Override
    public String getUrlEdit() {
        return Url.GROUP_MAIL_SERVER_EDIT_URL;
    }

    @Override
    public String getUrlList() {
        return Url.GROUP_MAIL_SERVER_LIST_URL;
    }

    @Override
    @PostConstruct
    public void init() {
        super.init();
    }

    @Override
    GroupMailServer newInstance() {
        return new GroupMailServer();
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public GroupMailServerService getEntityService() {
        return entityService;
    }

    public void setEntityService(GroupMailServerService entityService) {
        this.entityService = entityService;
    }

    @Override
    public IService<MailServer> getDetailService() {
        return detailService;
    }

    public void setDetailService(IService<MailServer> detailService) {
        this.detailService = detailService;
    }


}

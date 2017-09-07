package ua.shield.jsf.controller;

import org.primefaces.model.DualListModel;
import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.GroupMailServer;
import ua.shield.entity.MailAddress;
import ua.shield.entity.MailServer;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.Url;
import ua.shield.service.GroupMailServerService;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class GroupMailServerJsfController extends MainGroupJsfController<GroupMailServer,MailServer> {

    private GroupMailServer gropMailServer;
    private GroupMailServer selectedGroupMailServer;
    private DualListModel<MailServer> dualListModel;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{groupMailServerService}")
    private GroupMailServerService service;

    @ManagedProperty("#{mailServerService}")
    private IService<MailServer> serverService;

    public Set<GroupMailServer> mailServerSet() {
        return service.findAllByOwner();
    }


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
        gropMailServer = new GroupMailServer();
        List<MailServer> source = new ArrayList<>(serverService.findAllByOwner());
        List<MailServer> target = new ArrayList<>();
        dualListModel = new DualListModel<>(source, target);
    }

    public Set<GroupMailServer> groupMailServerSet(){
        return service.findAllByOwner();
    }

    @Override
    public void save() {
        gropMailServer.setMailServerSet(new HashSet<>(dualListModel.getTarget()));
        if (gropMailServer.getId() == null) {
            gropMailServer.setOwner(securityService.getRegisteredUser());
            service.add(gropMailServer);
        } else {
            service.update(gropMailServer);
        }
        FrontMessage.addMessage("Saved");
        init();
        Url.redirect(Url.GROUP_MAIL_SERVER_LIST_URL);
    }

    public void edit() {
        gropMailServer = selectedGroupMailServer;

        //находим разницу коллекций
        Set<MailServer> diffSet = serverService.findAllByOwner();
        diffSet.removeAll(selectedGroupMailServer.getMailServerSet());

        dualListModel = new DualListModel<>(new ArrayList<>(diffSet),
                new ArrayList<>(selectedGroupMailServer.getMailServerSet()));

        Url.redirect(Url.GROUP_MAIL_SERVER_EDIT_URL);
    }

    public void delete() {
        service.delete(selectedGroupMailServer);
        selectedGroupMailServer = null;
        FrontMessage.addMessage("Deleted");
        Url.redirect(Url.GROUP_MAIL_SERVER_LIST_URL);
    }

    public GroupMailServer getGropMailServer() {
        return gropMailServer;
    }

    public void setGropMailServer(GroupMailServer gropMailServer) {
        this.gropMailServer = gropMailServer;
    }

    public GroupMailServer getSelectedGroupMailServer() {
        return selectedGroupMailServer;
    }

    public void setSelectedGroupMailServer(GroupMailServer selectedGroupMailServer) {
        this.selectedGroupMailServer = selectedGroupMailServer;
    }

    public DualListModel<MailServer> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<MailServer> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public GroupMailServerService getService() {
        return service;
    }

    public void setService(GroupMailServerService service) {
        this.service = service;
    }

    public IService<MailServer> getServerService() {
        return serverService;
    }

    public void setServerService(IService<MailServer> serverService) {
        this.serverService = serverService;
    }
}

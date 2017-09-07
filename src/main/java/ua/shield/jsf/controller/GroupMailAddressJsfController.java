package ua.shield.jsf.controller;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.MailAddress;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.Url;
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
public class GroupMailAddressJsfController extends MainJsfController<GroupMailAddress> {


    private GroupMailAddress groupMailAddress;
    private GroupMailAddress selectedGroupMailAddress;
    private DualListModel<MailAddress> dualListModel;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{groupMailAddressService}")
    IService<GroupMailAddress> groupService;

    @ManagedProperty("#{mailAddressService}")
    IService<MailAddress> service;

    @PostConstruct
    @Override
    public void init() {
        groupMailAddress = new GroupMailAddress();
        List<MailAddress> source = new ArrayList<>(service.findAllByOwner());
        List<MailAddress> target = new ArrayList<>();
        dualListModel = new DualListModel<>(source, target);
    }

    @Override
    public String getUrlEdit() {
        return Url.GROUP_MAIL_ADDRESS_EDIT_URL;
    }

    @Override
    public String getUrlList() {
         return Url.GROUP_MAIL_ADDRESS_LIST_URL;
    }

    public Set<GroupMailAddress> groupMailAddressSet() {
        return groupService.findAllByOwner();
    }


    @Override
     public void save() {
        groupMailAddress.setMailAddressSet(new HashSet<>(dualListModel.getTarget()));
        if (groupMailAddress.getId() == null) {
            groupMailAddress.setOwner(securityService.getRegisteredUser());
            groupService.add(groupMailAddress);
        } else {
            groupService.update(groupMailAddress);
        }
        FrontMessage.addMessage("Saved");
        init();
        Url.redirect(Url.GROUP_MAIL_ADDRESS_LIST_URL);
    }


    public void edit() {
        groupMailAddress = selectedGroupMailAddress;

        //находим разницу коллекций
        Set<MailAddress> diffSet = service.findAllByOwner();
        diffSet.removeAll(selectedGroupMailAddress.getMailAddressSet());

        dualListModel = new DualListModel<>(new ArrayList<>(diffSet),
                new ArrayList<>(selectedGroupMailAddress.getMailAddressSet()));

        Url.redirect(Url.GROUP_MAIL_ADDRESS_EDIT_URL);
    }

    public void delete() {
        groupService.delete(selectedGroupMailAddress);
        selectedGroupMailAddress = null;
        FrontMessage.addMessage("Deleted");

        Url.redirect(Url.GROUP_MAIL_ADDRESS_LIST_URL);
    }

    public void onTransfer(TransferEvent event) {
//        StringBuilder builder = new StringBuilder();
//        for(Object item : event.getItems()) {
//            builder.append(((Theme) item).getName()).append("<br />");
//        }
//
//        FacesMessage msg = new FacesMessage();
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public GroupMailAddress getGroupMailAddress() {
        return groupMailAddress;
    }

    public void setGroupMailAddress(GroupMailAddress groupMailAddress) {
        this.groupMailAddress = groupMailAddress;
    }

    public GroupMailAddress getSelectedGroupMailAddress() {
        return selectedGroupMailAddress;
    }

    public void setSelectedGroupMailAddress(GroupMailAddress selectedGroupMailAddress) {
        this.selectedGroupMailAddress = selectedGroupMailAddress;
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public IService<GroupMailAddress> getGroupService() {
        return groupService;
    }

    public void setGroupService(IService<GroupMailAddress> groupService) {
        this.groupService = groupService;
    }

    public IService<MailAddress> getService() {
        return service;
    }

    public void setService(IService<MailAddress> service) {
        this.service = service;
    }

    public DualListModel<MailAddress> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<MailAddress> dualListModel) {
        this.dualListModel = dualListModel;
    }


}

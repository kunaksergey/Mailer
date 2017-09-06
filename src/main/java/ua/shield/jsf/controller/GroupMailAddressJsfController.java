package ua.shield.jsf.controller;

import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.MailAddress;
import ua.shield.exeption.UserIsNotRegisteredExeption;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.URL;
import ua.shield.service.GroupMailAddressService;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class GroupMailAddressJsfController extends MainJsfController<GroupMailAddress> {

    GroupMailAddress groupMailAddress;
    GroupMailAddress selectedGroupMailAddress;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{groupMailAddressService}")
    IService<GroupMailAddress> service;

    public void save() {
        if (groupMailAddress.getId() == null) {
            groupMailAddress.setOwner(securityService.getRegisteredUser());
            service.add(groupMailAddress);
        } else {
            service.update(groupMailAddress);
        }
        groupMailAddress = new GroupMailAddress();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(URL.MAIL_ADDRESS_LIST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        groupMailAddress = selectedGroupMailAddress;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(URL.MAIL_ADDRESS_EDIT_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        service.delete(selectedGroupMailAddress);
        selectedGroupMailAddress=null;
        FrontMessage.addMessage("Mail group was deleted");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(URL.MAIL_ADDRESS_LIST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public IService<GroupMailAddress> getService() {
        return service;
    }

    public void setService(IService<GroupMailAddress> service) {
        this.service = service;
    }
}

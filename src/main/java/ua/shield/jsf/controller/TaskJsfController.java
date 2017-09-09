package ua.shield.jsf.controller;

import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.GroupMailServer;
import ua.shield.entity.Message;
import ua.shield.entity.Task;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.Url;
import ua.shield.service.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class TaskJsfController extends MainJsfController<Task> {

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{taskService}")
    private IService<Task> entityService;

    @ManagedProperty("#{messageService}")
    private MessageService messageService;

    @ManagedProperty("#{groupMailAddressService}")
    private GroupMailAddressService groupMailAddressService;

    @ManagedProperty("#{groupMailServerService}")
    private GroupMailServerService groupMailServerService;

    @Override
    public String getUrlEdit() {
        return Url.TASK_EDIT_URL;
    }

    @Override
    public String getUrlList() {
        return Url.TASK_LIST_URL;
    }

    @Override
    @PostConstruct
    public void init() {
        super.init();
    }

    @Override
    Task newInstance() {
        return new Task();
    }

   //возвращаем набор сообщений пользователя
    public Set<Message> getMessageSet() {
        return messageService.findAllByOwner();
    }

    //возвращаем группы адрессов пользователя
    public Set<GroupMailAddress> getGroupMailAddressSet() {
        return groupMailAddressService.findAllByOwner();
    }

    //возвращаем группы серверов пользователя
    public Set<GroupMailServer> getGroupMailServerSet() {
        return groupMailServerService.findAllByOwner();
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public IService<Task> getEntityService() {
        return entityService;
    }

    public void setEntityService(IService<Task> entityService) {
        this.entityService = entityService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public GroupMailAddressService getGroupMailAddressService() {
        return groupMailAddressService;
    }

    public void setGroupMailAddressService(GroupMailAddressService groupMailAddressService) {
        this.groupMailAddressService = groupMailAddressService;
    }

    public GroupMailServerService getGroupMailServerService() {
        return groupMailServerService;
    }

    public void setGroupMailServerService(GroupMailServerService groupMailServerService) {
        this.groupMailServerService = groupMailServerService;
    }


}

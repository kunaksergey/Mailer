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

    Task task;
    Task selectedTask;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{taskService}")
    TaskService service;

    @ManagedProperty("#{messageService}")
    MessageService messageService;

    @ManagedProperty("#{groupMailAddressService}")
    GroupMailAddressService groupMailAddressService;

    @ManagedProperty("#{groupMailServerService}")
    GroupMailServerService groupMailServerService;


    //Список заданий текущего пользователя
    public Set<Task> taskSet() {
        return service.findAllByOwner();
    }

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
        task = new Task();
    }

    public void edit() {
        task = selectedTask;
        Url.redirect(Url.TASK_EDIT_URL);
    }

    public void delete() {
        service.delete(selectedTask);
        selectedTask=null;
        FrontMessage.addMessage("Deleted");
        Url.redirect(Url.TASK_LIST_URL);
    }

    @Override
    public void save() {
        if (task.getId() == null) {
            task.setOwner(securityService.getRegisteredUser());
            service.add(task);
        } else {
            service.update(task);
        }
        FrontMessage.addMessage("Saved");
        init();
        Url.redirect(Url.TASK_LIST_URL);
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public SecurityServiceImpl getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public TaskService getService() {
        return service;
    }

    public void setService(TaskService service) {
        this.service = service;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
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

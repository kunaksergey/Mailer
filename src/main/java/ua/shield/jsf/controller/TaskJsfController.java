package ua.shield.jsf.controller;

import ua.shield.entity.Schedule;
import ua.shield.entity.Task;
import ua.shield.exeption.UserIsNotRegisteredExeption;
import ua.shield.service.SecurityServiceImpl;
import ua.shield.service.TaskService;
import ua.shield.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class TaskJsfController {

    Task task;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{taskService}")
    TaskService service;

    //Список заданий текущего пользователя
    public Set<Task> taskSet(){
//        try {
//            return service.findAllByOwner(securityService.getRegisteredUser());
//        } catch (UserIsNotRegisteredExeption userIsNotRegisteredExeption) {
//            return new HashSet<Task>();
//        }

        Set<Task> setTask=new HashSet<>();
        setTask.add(new Task(1,"One task"));
        setTask.add(new Task(2,"Two task"));
        setTask.add(new Task(3,"Three task"));
        setTask.add(new Task(4,"For task"));
        return setTask;
    }

    public Map<Integer,Task> taskMap(){
        return taskSet().stream().collect(Collectors.toMap(Task::getId,c->c));
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
}

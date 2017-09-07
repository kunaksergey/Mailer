package ua.shield.jsf.converter;

import ua.shield.entity.Task;
import ua.shield.helper.FrontMessage;
import ua.shield.service.IService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Created by sa on 05.09.17.
 */
@ManagedBean
@RequestScoped
public class TaskConverter extends FaceConverter<Task> {

    @ManagedProperty("#{taskService}")
    IService<Task> service;

    @Override
    public IService<Task> getService() {
        return service;
    }

    @Override
    public void setService(IService<Task> service) {
        this.service = service;
    }
}

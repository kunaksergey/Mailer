package ua.shield.jsf.converter;

import ua.shield.entity.Task;
import ua.shield.helper.FrontMessage;
import ua.shield.service.IService;

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
    IService service;

    @Override
    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }
}

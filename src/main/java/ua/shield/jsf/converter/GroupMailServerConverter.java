package ua.shield.jsf.converter;

import ua.shield.entity.GroupMailServer;
import ua.shield.entity.Task;
import ua.shield.service.IService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by sa on 05.09.17.
 */
@ManagedBean
@RequestScoped
public class GroupMailServerConverter extends FaceConverter<GroupMailServer> {

    @ManagedProperty("#{groupMailServerService}")
    IService<GroupMailServer> service;

    @Override
    public IService<GroupMailServer> getService() {
        return service;
    }

    @Override
    public void setService(IService<GroupMailServer> service) {
        this.service = service;
    }
}

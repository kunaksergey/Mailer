package ua.shield.jsf.converter;

import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.GroupMailServer;
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
public class GroupMailAddressConverter extends FaceConverter<GroupMailAddress> {

    @ManagedProperty("#{groupMailAddressService}")
    IService<GroupMailAddress> service;

    @Override
    public IService<GroupMailAddress> getService() {
        return service;
    }

    @Override
    public void setService(IService<GroupMailAddress> service) {
        this.service = service;
    }
}

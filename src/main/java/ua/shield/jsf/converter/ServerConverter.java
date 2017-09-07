package ua.shield.jsf.converter;

import ua.shield.entity.MailAddress;
import ua.shield.entity.MailServer;
import ua.shield.service.IService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by sa on 05.09.17.
 */
@ManagedBean
@RequestScoped
public class ServerConverter extends FaceConverter<MailServer> {

    @ManagedProperty("#{mailServerService}")
    IService<MailServer> service;


    @Override
    public IService<MailServer> getService() {
        return service;
    }

    @Override
    public void setService(IService<MailServer> service) {
        this.service=service;
    }
}

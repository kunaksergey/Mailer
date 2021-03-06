package ua.shield.jsf.converter;

import ua.shield.entity.MailAddress;
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
public class EmailConverter extends FaceConverter<MailAddress> {

    @ManagedProperty("#{mailAddressService}")
    IService<MailAddress> service;

    @Override
    public IService<MailAddress> getService() {
        return service;
    }

    @Override
    public void setService(IService<MailAddress> service) {
        this.service = service;
    }


}

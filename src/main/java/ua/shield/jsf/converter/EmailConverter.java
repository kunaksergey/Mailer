package ua.shield.jsf.converter;

import ua.shield.entity.MailAddress;
import ua.shield.service.IService;

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
    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }
}

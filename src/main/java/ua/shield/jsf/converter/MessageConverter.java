package ua.shield.jsf.converter;

import ua.shield.entity.MailAddress;
import ua.shield.entity.Message;
import ua.shield.service.IService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


/**
 * Created by sa on 07.09.17.
 */
@ManagedBean
@RequestScoped
public class MessageConverter extends FaceConverter<Message> {
    @ManagedProperty("#{messageService}")
    private IService<Message> service;

    @Override
    public IService<Message> getService() {
        return service;
    }

    @Override
    public void setService(IService<Message> service) {
        this.service = service;
    }
}

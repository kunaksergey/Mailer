package ua.shield.jsf.controller;

import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.IOwned;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.Url;
import ua.shield.service.IService;
import ua.shield.service.SecurityServiceImpl;

import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
abstract public class MainJsfController<T extends IOwned> {

    private T entity;
    private T selectedEntity;

    abstract public String getUrlEdit();
    abstract public String getUrlList();
    abstract IService<T> getEntityService();
    abstract T newInstance();
    abstract public SecurityServiceImpl getSecurityService();

    public Set<T> entitySet() {
        return getEntityService().findAllByOwner();
    }

    public void init(){
        entity=newInstance();
    }
    public void create(){
        init();
        Url.redirect(getUrlEdit());
    }

    public void edit() {
        entity = selectedEntity;
        Url.redirect(getUrlEdit());
    }

    public void delete() {
        getEntityService().delete(selectedEntity);
        selectedEntity = null;
        FrontMessage.addMessage("Deleted");
        Url.redirect(getUrlList());
    }

    public void save() {
        if (entity.getId() == null) {
            entity.setOwner(getSecurityService().getRegisteredUser());
            getEntityService().add(entity);
        } else {
            getEntityService().update(entity);
        }
        entity = newInstance();
        Url.redirect(getUrlList());
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public T getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        this.selectedEntity = selectedEntity;
    }


}

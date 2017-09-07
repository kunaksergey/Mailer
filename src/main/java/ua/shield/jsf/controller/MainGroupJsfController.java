package ua.shield.jsf.controller;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import ua.shield.helper.FrontMessage;

import javax.faces.application.FacesMessage;

/**
 * Created by sa on 06.09.17.
 */
abstract public class MainGroupJsfController<T,E> extends MainJsfController<T> {
    public void onTransfer(TransferEvent event) {
        FrontMessage.addMessage("Items Transferred");
    }

    public void onSelect(SelectEvent event) {
        FrontMessage.addMessage("Item Selected");
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FrontMessage.addMessage("Item Unselected");
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FrontMessage.addMessage("List Reordered");
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }
}

package ua.shield.jsf.controller;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import ua.shield.entity.IOwnedDetail;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.Url;
import ua.shield.service.IService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 06.09.17.
 */
abstract public class MainGroupJsfController<T extends IOwnedDetail<E>, E> extends MainJsfController<T> {

    private DualListModel<E> dualListModel;

    abstract IService<E> getDetailService();

    public void init() {
        super.init();
        List<E> source = new ArrayList<>(getDetailService().findAllByOwner());
        List<E> target = new ArrayList<>();
        setDualListModel(new DualListModel<>(source, target));
    }

    @Override
    public void save() {
        getEntity().setDetailSet(new HashSet<>(getDualListModel().getTarget()));
        super.save();
    }

    @Override
    public void edit() {
        setEntity(getSelectedEntity());

        //находим разницу коллекций
        Set<E> diffSet = getDetailService().findAllByOwner();
        diffSet.removeAll(getSelectedEntity().getDetailSet());

        setDualListModel(new DualListModel<>(new ArrayList<>(diffSet),
                new ArrayList<>(getSelectedEntity().getDetailSet())));
        Url.redirect(getUrlEdit());
    }

    public DualListModel<E> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<E> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public void onTransfer(TransferEvent event) {
        FrontMessage.addMessage("Items Transferred");
    }

    public void onSelect(SelectEvent event) {
        FrontMessage.addMessage("Item Selected");
    }

    public void onUnselect(UnselectEvent event) {
        FrontMessage.addMessage("Item Unselected");
    }

    public void onReorder() {
        FrontMessage.addMessage("List Reordered");
    }

}

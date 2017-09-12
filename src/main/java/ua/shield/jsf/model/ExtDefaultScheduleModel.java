package ua.shield.jsf.model;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import ua.shield.entity.ExtScheduleEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by sa on 11.09.17.
 */
public class ExtDefaultScheduleModel extends DefaultScheduleModel implements IScheduleModel {

    @Override
    public void addAllEvent(List<ExtScheduleEvent> listEvent) {
        listEvent.forEach(e->addEvent(e));
    }

    //If we don't have id, it will be genetated
    @Override
    public void addEvent(ScheduleEvent event) {
        getEvents().add(event);
    }

}

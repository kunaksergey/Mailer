package ua.shield.jsf.model;

import org.primefaces.model.ScheduleModel;
import ua.shield.entity.ExtScheduleEvent;

import java.util.List;

/**
 * Created by sa on 11.09.17.
 */
public interface IScheduleModel extends ScheduleModel {
    void addAllEvent(List<ExtScheduleEvent> listEvent);
}

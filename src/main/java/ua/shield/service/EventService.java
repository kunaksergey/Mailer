package ua.shield.service;

import ua.shield.entity.ExtScheduleEvent;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sa on 11.09.17.
 */
public interface EventService extends IService<ExtScheduleEvent> {
    List<ExtScheduleEvent> findAllLessThanTimeAndEditable(LocalDateTime localDateTime, boolean editable);
}

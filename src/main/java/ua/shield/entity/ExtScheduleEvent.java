package ua.shield.entity;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import ua.shield.enumer.TypeEvent;

import java.util.Date;

/**
 * Created by sa on 05.09.17.
 */
public class ExtScheduleEvent extends DefaultScheduleEvent {
    private TypeEvent typeEvent=TypeEvent.ONCE;
    private Task task;

    public ExtScheduleEvent() {
    }

    public ExtScheduleEvent(String title, Date start, Date end) {
        super(title,start,end);
    }

    public ExtScheduleEvent(String title, Date start, Date end, boolean allDay) {
        super(title,start,end,allDay);
    }

    public ExtScheduleEvent(String title, Date start, Date end, String styleClass) {
        super(title,start,end,styleClass);
    }

    public ExtScheduleEvent(String title, Date start, Date end, Object data) {
        super(title,start,end,data);
    }


    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

package ua.shield.entity;

import org.primefaces.model.*;
import ua.shield.enumer.DateStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sa on 05.09.17.
 */
public class ExtScheduleEvent implements ScheduleEvent, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="Title")
    private String title;

    @Column(name="startDate")
    private Date startDate;

    @Column(name="endDate")
    private Date endDate;

    @Column(name="nextRunDate")
    private Date nextRunDate; //слудующий запуск

    private Task task;

    @Column(name="dateStrategy")
    @Enumerated(EnumType.STRING)
    private DateStrategy dateStrategy = DateStrategy.ONCE; //тип интервала

    @Column(name="day")
    private int day;//интервал в днях

    @Column(name="hour")
    private int hour;//интервал в часах

    @Column(name="editable")
    private boolean editable = true;

    @Column(name="description")
    private String description;

    public ExtScheduleEvent() {
    }

    public ExtScheduleEvent(String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getId() {
        return ""+id;
    }

    @Override
    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }


     public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getNextRunDate() {
        return nextRunDate;
    }

    public void setNextRunDate(Date nextRunDate) {
        this.nextRunDate = nextRunDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public DateStrategy getDateStrategy() {
        return dateStrategy;
    }

    public void setDateStrategy(DateStrategy dateStrategy) {
        this.dateStrategy = dateStrategy;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getUrl() {
        //do nothing
        return null;
    }

    @Override
    public Object getData() {
        //do nothing
        return null;
    }

    @Override
    public boolean isAllDay() {
        //do nothing
        return false;
    }

    @Override
    public String getStyleClass() {
        //do nothing
        return null;
    }
}

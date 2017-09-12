package ua.shield.entity;

import org.primefaces.model.*;
import ua.shield.enumer.DateStrategy;
import ua.shield.helper.ConverterDateAndLocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by sa on 05.09.17.
 */
@Entity
@Table(name="schedule_event")
public class ExtScheduleEvent implements ScheduleEvent, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="Title")
    private String title;

    @Column(name="createDate")
    private LocalDateTime createDate;

    @Column(name="startDate")
    private LocalDateTime startDate;

    @Column(name="endDate")
    private LocalDateTime endDate;

    @Column(name="nextRunDate")
    private Date nextRunDate; //слудующий запуск

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "TASK_ID")
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


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User owner;

    @Column(name="count")
    private int count;

    public ExtScheduleEvent() {
    }

    public ExtScheduleEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ExtScheduleEvent(String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = ConverterDateAndLocalDateTime.DateToLocalDateTime(startDate);
        this.endDate = ConverterDateAndLocalDateTime.DateToLocalDateTime(endDate);
    }


    @Override
    public String getId() {
        return (id==null)?null:id.toString();
    }

    @Override
    public void setId(String id) {
        this.id=Integer.valueOf(id);
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
        return (startDate==null)?null:ConverterDateAndLocalDateTime.LocalDateTimeToDate(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = ConverterDateAndLocalDateTime.DateToLocalDateTime(startDate);
    }

    @Override
    public Date getEndDate() {
        return (endDate==null)?null:ConverterDateAndLocalDateTime.LocalDateTimeToDate(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = ConverterDateAndLocalDateTime.DateToLocalDateTime(endDate);
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtScheduleEvent that = (ExtScheduleEvent) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return title != null ? title.equals(that.title) : that.title == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}

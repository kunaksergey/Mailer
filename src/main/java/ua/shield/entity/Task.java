package ua.shield.entity;

import ua.shield.enumer.TaskStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name="task")
public class Task implements IOwned,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    //наименования задания
    @Column(name="title")
    private String title;

    //сообщение
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGE_ID")
    private Message message;

   //статус задания 0-подготовлено,1-в работе, 2-закончено
    //@Column(name="status")
    //@Enumerated(EnumType.ORDINAL)
    @Transient
    TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User owner;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_EMAIL_ID")
    private GroupMailAddress groupMailAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_SERVER_ID")
    private GroupMailServer groupMailServer;

    //события
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "task",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<ExtScheduleEvent> events;

    public Task() {
    }

    public Task(Integer id,String title) {
        this.id=id;
        this.title = title;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public GroupMailAddress getGroupMailAddress() {
        return groupMailAddress;
    }

    public void setGroupMailAddress(GroupMailAddress groupMailAddress) {
        this.groupMailAddress = groupMailAddress;
    }

    public GroupMailServer getGroupMailServer() {
        return groupMailServer;
    }

    public void setGroupMailServer(GroupMailServer groupMailServer) {
        this.groupMailServer = groupMailServer;
    }

    public List<ExtScheduleEvent> getEvents() {
        return events;
    }

    public void setEvents(List<ExtScheduleEvent> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        return title != null ? title.equals(task.title) : task.title == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return title;
    }


}


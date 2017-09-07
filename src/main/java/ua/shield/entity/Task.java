package ua.shield.entity;

import ua.shield.enumer.TaskStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name="task")
public class Task implements IOwnedId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    //наименования задания
    @Column(name="title")
    private String title;

    // рассписание задания
    @Transient
    private Schedule schedule;

    //сообщение
    @OneToOne
    @JoinColumn(name = "MESSAGE_ID")
    private Message message;

    //количество запусков
    //@Column(name="count")
    @Transient
    private int count;

    //статус задания 0-подготовлено,1-в работе, 2-закончено
    //@Column(name="status")
    //@Enumerated(EnumType.ORDINAL)
    @Transient
    TaskStatus status;

    //старт задания
    @Transient
    private LocalDateTime startTime;

    //последний запуск
    @Transient
    private LocalDateTime lastInvokeTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User owner;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_EMAIL_ID")
    private GroupMailAddress groupMailAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_SERVER_ID")
    private GroupMailServer groupMailServer;

    //следующий запуск задания
    @Transient
    private LocalDateTime nextStartTime;


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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

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


    @Override
    public String toString() {
        return title;
    }


}


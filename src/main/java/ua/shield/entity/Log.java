package ua.shield.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 12.09.17.
 */
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "EVENT_ID")
    private ExtScheduleEvent event;

    private String log;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExtScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ExtScheduleEvent event) {
        this.event = event;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

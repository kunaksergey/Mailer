package ua.shield.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name = "group_mail_server")
public class GroupMailServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    //наименования группы
    @Column(name="title")
    private String title;

    //список email-лов для рассылки
    @ManyToMany
    @JoinTable (name = "group_mail_server_detail",
            joinColumns = @JoinColumn (name = "GROUP_ID"),
            inverseJoinColumns =@JoinColumn(name = "SERVER_ID"))
    private Set<MailServer> mailServerSet;

    //user-владелец группы серверов
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<MailServer> getMailServerSet() {
        return mailServerSet;
    }

    public void setMailServerSet(Set<MailServer> mailServerSet) {
        this.mailServerSet = mailServerSet;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

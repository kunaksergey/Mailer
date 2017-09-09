package ua.shield.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name = "group_mail_server")
public class GroupMailServer implements IOwnedDetail<MailServer>,Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

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

    public Set<MailServer> getMailServerSet() {
        return mailServerSet;
    }

    public void setMailServerSet(Set<MailServer> mailServerSet) {
        this.mailServerSet = mailServerSet;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMailServer that = (GroupMailServer) o;

        return title != null ? title.equals(that.title) : that.title == null;

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public Set<MailServer> getDetailSet() {
        return mailServerSet;
    }

    @Override
    public void setDetailSet(Set<MailServer> detailSet) {
        this.mailServerSet=detailSet;
    }
}

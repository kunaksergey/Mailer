package ua.shield.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name="group_mail_address")
public class GroupMailAddress implements IOwnedId{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    //наименования группы
    @Column(name="title")
    private String title;

    //список email-лов для рассылки
    @ManyToMany
    @JoinTable (name = "group_mail_address_detail",
            joinColumns = @JoinColumn (name = "GROUP_ID"),
            inverseJoinColumns =@JoinColumn(name = "EMAIL_ID"))
    private Set<MailAddress> mailAddressSet;

    //user-владелец группы адрессов
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

    public Set<MailAddress> getMailAddressSet() {
        return mailAddressSet;
    }

    public void setMailAddressSet(Set<MailAddress> mailAddressSet) {
        this.mailAddressSet = mailAddressSet;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMailAddress that = (GroupMailAddress) o;

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
}

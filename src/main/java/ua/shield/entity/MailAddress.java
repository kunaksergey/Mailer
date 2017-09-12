package ua.shield.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name="mail_address")
public class MailAddress implements IOwned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="email")
    private String emailAddress;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User owner;

    @ManyToMany
    @JoinTable (name = "group_mail_address_detail",
            joinColumns = @JoinColumn (name = "EMAIL_ID"),
            inverseJoinColumns =@JoinColumn(name = "GROUP_ID"))
    private Set<GroupMailAddress> groupMailAddressSet;


    public MailAddress() {
    }

    public MailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public MailAddress(String emailAddress, User owner) {
        this.emailAddress = emailAddress;
        this.owner = owner;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<GroupMailAddress> getGroupMailAddressSet() {
        return groupMailAddressSet;
    }

    public void setGroupMailAddressSet(Set<GroupMailAddress> groupMailAddressSet) {
        this.groupMailAddressSet = groupMailAddressSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MailAddress that = (MailAddress) o;

        return emailAddress != null ? emailAddress.equals(that.emailAddress) : that.emailAddress == null;

    }

    @Override
    public int hashCode() {
        return emailAddress != null ? emailAddress.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MailAddress{" +
                "emailAddress='" + emailAddress + '\'' +
                ", owner=" + owner +
                '}';
    }


}

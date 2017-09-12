package ua.shield.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name="users")
public class User {
    private static final int DISABLE = 0;
    private static final int ENABLE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;


    //группы почтовых ящиков для рассылки
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner",cascade=CascadeType.ALL)
    private List<GroupMailAddress> groupsMailAdress;

    //группы серверов для рассылки
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner",cascade=CascadeType.ALL)
    private List<GroupMailServer> groupsMailServer;

    //список адрессов пользователя
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner",cascade=CascadeType.ALL)
    private List<MailAddress> mailAddresses;

    //список серверов пользователя
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner",cascade=CascadeType.ALL)
    private List<MailServer> mailServers;

    //задания
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner",cascade=CascadeType.ALL)
    private List<Task> tasks;

    //события
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner",cascade=CascadeType.ALL)
    private List<ExtScheduleEvent> events;

    //сообщения
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner",cascade=CascadeType.ALL)
    private List<Message> messages;

    //логин
    @Column(name="login")
    private String login;

    //пароль
    @Column(name="password")
    private String password;

    //e-mail
    @Column(name="email")
    private String email;

    // включен ли пользователь,false-отключен, 1-включен

    //@Column(name="status")
    @Transient
    private boolean isActive=true;

    //список ролей
    @ManyToMany
    @JoinTable (name = "user_role_detail",
            joinColumns = @JoinColumn (name = "USER_ID"),
            inverseJoinColumns =@JoinColumn(name = "ROLE_ID"))
    Set<Role> roles=new HashSet<>();


    public User() {
    }

    public User(int id, String login) {
        this.id=id;
        this.login = login;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<GroupMailAddress> getGroupsMailAdress() {
        return groupsMailAdress;
    }

    public void setGroupsMailAdress(List<GroupMailAddress> groupsMailAdress) {
        this.groupsMailAdress = groupsMailAdress;
    }

    public List<GroupMailServer> getGroupsMailServer() {
        return groupsMailServer;
    }

    public void setGroupsMailServer(List<GroupMailServer> groupsMailServer) {
        this.groupsMailServer = groupsMailServer;
    }

    public List<MailAddress> getMailAddresses() {
        return mailAddresses;
    }

    public void setMailAddresses(List<MailAddress> mailAddresses) {
        this.mailAddresses = mailAddresses;
    }

    public List<MailServer> getMailServers() {
        return mailServers;
    }

    public void setMailServers(List<MailServer> mailServers) {
        this.mailServers = mailServers;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<ExtScheduleEvent> getEvents() {
        return events;
    }

    public void setEvents(List<ExtScheduleEvent> events) {
        this.events = events;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login != null ? login.equals(user.login) : user.login == null;

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}

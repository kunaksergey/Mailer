package ua.shield.entity;

import ua.shield.enumer.Protocol;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name="mail_server")
public class MailServer implements IOwned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User owner;

    @ManyToMany
    @JoinTable (name = "group_mail_server_detail",
            joinColumns = @JoinColumn (name = "SERVER_ID"),
            inverseJoinColumns =@JoinColumn(name = "GROUP_ID"))
    private Set<GroupMailServer> groupMailServerSet;

    //dns сервера
    @Column(name="host")
    private String host;

    //порт сервера
    @Column(name="port")
    private int port;

    //имя пользователя
    @Column(name="username")
    private String username;

    //пароль пользователя
    @Column(name="password")
    private String password;

    //пользователь от которого отправляется
    @Column(name="sender")
    private String sender;

    @Column(name="protocol")
    @Enumerated(EnumType.STRING)
    private Protocol protocol;

    //true-требуеся аунтификация, false-нет
    @Column(name="smtpAuth")
    private boolean smtpAuth;

    @Column(name="startTls")
    private boolean startTls;

    public MailServer() {
    }

    public MailServer(User owner, String host, int port, String username, String password, String sender, Protocol protocol, boolean smtpAuth) {
        this.owner = owner;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.sender = sender;
        this.protocol = protocol;
        this.smtpAuth = smtpAuth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public boolean isSmtpAuth() {
        return smtpAuth;
    }

    public boolean isStartTls() {
        return startTls;
    }

    public void setStartTls(boolean startTls) {
        this.startTls = startTls;
    }

    public void setSmtpAuth(boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public Set<GroupMailServer> getGroupMailServerSet() {
        return groupMailServerSet;
    }

    public void setGroupMailServerSet(Set<GroupMailServer> groupMailServerSet) {
        this.groupMailServerSet = groupMailServerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MailServer that = (MailServer) o;

        if (port != that.port) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        return protocol == that.protocol;

    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MailServer{" +
                "owner=" + owner +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", protocol=" + protocol +
                ", smtpAuth=" + smtpAuth +
                '}';
    }
}



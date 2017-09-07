package ua.shield.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Entity
@Table(name="message")
public class Message implements IOwnedId{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User owner;

    //Заголовок сообщения
    @Column(name="title")
    private String title;

    //текст сообщения
    @Column(name="text")
    private String text;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return title != null ? title.equals(message.title) : message.title == null;

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                '}';
    }
}

package ua.shield.entity;

import java.util.Set;

/**
 * Created by sa on 05.09.17.
 */
public interface IOwned {
    Integer getId();
    User getOwner();
    void setOwner(User owner);
}

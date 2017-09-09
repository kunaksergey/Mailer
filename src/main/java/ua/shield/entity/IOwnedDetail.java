package ua.shield.entity;

import java.util.Set;

/**
 * Created by sa on 05.09.17.
 */
public interface IOwnedDetail<T> extends IOwned {
    Set<T> getDetailSet();
    void setDetailSet(Set<T> detailSet);
}

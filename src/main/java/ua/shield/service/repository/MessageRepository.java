package ua.shield.service.repository;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.Message;
import ua.shield.entity.User;

import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
public interface MessageRepository extends CrudRepository<Message,Integer> {
    Set<Message> findAllByOwner(User owner);
}

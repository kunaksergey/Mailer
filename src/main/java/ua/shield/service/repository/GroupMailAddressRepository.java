package ua.shield.service.repository;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.MailAddress;
import ua.shield.entity.Message;
import ua.shield.entity.User;

import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
public interface GroupMailAddressRepository extends CrudRepository<GroupMailAddress,Integer> {
    Set<GroupMailAddress> findAllByOwner(User owner);
}

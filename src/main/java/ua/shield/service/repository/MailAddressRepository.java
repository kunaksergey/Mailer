package ua.shield.service.repository;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.MailAddress;
import ua.shield.entity.Message;
import ua.shield.entity.Task;
import ua.shield.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
public interface MailAddressRepository extends CrudRepository<MailAddress,Integer> {
    Set<MailAddress> findAllByOwner(User owner);

}

package ua.shield.service.repository;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.User;

/**
 * Created by sa on 01.09.17.
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    public User findUserByLogin(String login);
    public User findUserByEmail(String email);
}

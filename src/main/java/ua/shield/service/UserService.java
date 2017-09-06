package ua.shield.service;

import ua.shield.entity.User;

/**
 * Created by sa on 02.09.17.
 */
public interface UserService extends IService<User>{
    User findUserByLogin(String login);
}

package ua.shield.service;

import ua.shield.entity.User;
import ua.shield.exeption.UserIsNotRegisteredExeption;

/**
 * Created by sa on 05.09.17.
 */
public interface SecurityService {
    User getRegisteredUser();
}

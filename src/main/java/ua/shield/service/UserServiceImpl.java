package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.User;
import ua.shield.service.repository.UserRepository;

import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Set<User> findAllByOwner() {
        return null;
    }

    @Override
    public User find(User task) {
        return userRepository.findOne(task.getId());
    }

    @Override
    public User add(User task) {
        return userRepository.save(task);
    }

    @Override
    public Iterable<User> addAll(Iterable<User> iterable) {
        return userRepository.save(iterable);
    }

    @Override
    public User update(User task) {
        return userRepository.save(task);
    }

    @Override
    public Iterable<User> updateAll(Iterable<User> iterable) {
        return userRepository.save(iterable);
    }

    @Override
    public void delete(User task) {
        userRepository.delete(task);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteById(Iterable<User> iterable) {
        userRepository.delete(iterable);
    }

}

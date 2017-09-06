package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Message;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.service.repository.RoleRepository;

import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public Iterable<Role> findAll() {
        return null;
    }

    @Override
    public Role find(Role entity) {
        return null;
    }

    @Override
    public Set<Role> findAllByOwner() {
        return null;
    }

    @Override
    public Role add(Role entity) {
        return null;
    }

    @Override
    public Iterable<Role> addAll(Iterable<Role> iterable) {
        return null;
    }

    @Override
    public Role update(Role entity) {
        return null;
    }

    @Override
    public Iterable<Role> updateAll(Iterable<Role> iterable) {
        return null;
    }

    @Override
    public void delete(Role entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteById(Iterable<Role> iterable) {

    }
}

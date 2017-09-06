package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.MailAddress;
import ua.shield.entity.User;
import ua.shield.service.repository.GroupMailAddressRepository;
import ua.shield.service.repository.MailAddressRepository;

import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Service("groupMailAddressService")
public class GroupMailAddressServiceImpl implements GroupMailAddressService {
    @Autowired
    private GroupMailAddressRepository groupMailAddressRepository;

    @Autowired
    SecurityService securityService;

    @Override
    public GroupMailAddress findById(int id) {
        return groupMailAddressRepository.findOne(id);
    }

    @Override
    public Iterable<GroupMailAddress> findAll() {
        return groupMailAddressRepository.findAll();
    }

    @Override
    public GroupMailAddress find(GroupMailAddress groupMailAddress) {
        return groupMailAddressRepository.findOne(groupMailAddress.getId());
    }

    @Override
    public Set<GroupMailAddress> findAllByOwner() {
        return  groupMailAddressRepository.findAllByOwner(securityService.getRegisteredUser());
    }

    @Override
    public GroupMailAddress add(GroupMailAddress groupMailAddress) {
        return groupMailAddressRepository.save(groupMailAddress);
    }

    @Override
    public Iterable<GroupMailAddress> addAll(Iterable<GroupMailAddress> iterable) {
        return groupMailAddressRepository.save(iterable);
    }

    @Override
    public GroupMailAddress update(GroupMailAddress groupMailAddress) {
        return groupMailAddressRepository.save(groupMailAddress);
    }

    @Override
    public Iterable<GroupMailAddress> updateAll(Iterable<GroupMailAddress> iterable) {
        return groupMailAddressRepository.save(iterable);
    }

    @Override
    public void delete(GroupMailAddress groupMailAddress) {
        groupMailAddressRepository.delete(groupMailAddress);
    }

    @Override
    public void deleteAll() {
        groupMailAddressRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        groupMailAddressRepository.delete(id);
    }

    @Override
    public void deleteById(Iterable<GroupMailAddress> iterable) {
        groupMailAddressRepository.delete(iterable);
    }
}

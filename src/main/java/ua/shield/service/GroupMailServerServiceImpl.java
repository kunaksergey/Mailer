package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.GroupMailAddress;
import ua.shield.entity.GroupMailServer;
import ua.shield.entity.User;
import ua.shield.service.repository.GroupMailAddressRepository;
import ua.shield.service.repository.GroupMailServerRepository;

import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Service("groupMailServerService")
public class GroupMailServerServiceImpl implements GroupMailServerService {
    @Autowired
    private GroupMailServerRepository groupMailServerRepository;

    @Autowired
    SecurityService securityService;

    @Override
    public GroupMailServer findById(int id) {
        return groupMailServerRepository.findOne(id);
    }

    @Override
    public Iterable<GroupMailServer> findAll() {
        return groupMailServerRepository.findAll();
    }

    @Override
    public GroupMailServer find(GroupMailServer groupMailServer) {
        return groupMailServerRepository.findOne(groupMailServer.getId());
    }

    @Override
    public Set<GroupMailServer> findAllByOwner() {
        return groupMailServerRepository.findAllByOwner(securityService.getRegisteredUser());
    }

    @Override
    public GroupMailServer add(GroupMailServer groupMailServer) {
        return groupMailServerRepository.save(groupMailServer);
    }

    @Override
    public Iterable<GroupMailServer> addAll(Iterable<GroupMailServer> iterable) {
        return groupMailServerRepository.save(iterable);
    }

    @Override
    public GroupMailServer update(GroupMailServer groupMailServer) {
        return groupMailServerRepository.save(groupMailServer);
    }

    @Override
    public Iterable<GroupMailServer> updateAll(Iterable<GroupMailServer> iterable) {
          return groupMailServerRepository.save(iterable);
    }

    @Override
    public void delete(GroupMailServer groupMailServer) {
        groupMailServerRepository.delete(groupMailServer);
    }

    @Override
    public void deleteAll() {
        groupMailServerRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        groupMailServerRepository.delete(id);
    }

    @Override
    public void deleteById(Iterable<GroupMailServer> iterable) {
        groupMailServerRepository.delete(iterable);
    }
}

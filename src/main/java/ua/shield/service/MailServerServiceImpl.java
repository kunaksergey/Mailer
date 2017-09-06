package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.MailAddress;
import ua.shield.entity.MailServer;
import ua.shield.entity.User;
import ua.shield.service.repository.MailAddressRepository;
import ua.shield.service.repository.MailServerRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Service("mailServerService")
public class MailServerServiceImpl implements MailServerService {
    @Autowired
    private MailServerRepository mailServerRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public MailServer findById(int id) {
        return mailServerRepository.findOne(id);
    }

    @Override
    public Iterable<MailServer> findAll() {
        return mailServerRepository.findAll();
    }

     @Override
    public MailServer find(MailServer mailServer) {
        return mailServerRepository.findOne(mailServer.getId());
    }

    @Override
    public Set<MailServer> findAllByOwner() {
        return  mailServerRepository.findAllByOwner(securityService.getRegisteredUser());
    }

    @Override
    public MailServer add(MailServer mailServer) {
        return mailServerRepository.save(mailServer);
    }

    @Override
    public Iterable<MailServer> addAll(Iterable<MailServer> iterable) {
        return mailServerRepository.save(iterable);
    }

    @Override
    public MailServer update(MailServer mailServer) {
        return mailServerRepository.save(mailServer);
    }

    @Override
    public Iterable<MailServer> updateAll(Iterable<MailServer> iterable) {
        return mailServerRepository.save(iterable);
    }

    @Override
    public void delete(MailServer mailServer) {
        mailServerRepository.delete(mailServer);
    }

    @Override
    public void deleteAll() {
        mailServerRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        mailServerRepository.delete(id);
    }

    @Override
    public void deleteById(Iterable<MailServer> iterable) {
        mailServerRepository.delete(iterable);
    }


}

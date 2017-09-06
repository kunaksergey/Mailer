package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.MailAddress;
import ua.shield.entity.User;
import ua.shield.service.repository.MailAddressRepository;

import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Service("mailAddressService")
public class MailAddressServiceImpl implements MailAddressService {
    @Autowired
    private MailAddressRepository mailAddressRepository;

    @Autowired
    SecurityService securityService;
    @Override
    public MailAddress findById(int id) {
        return mailAddressRepository.findOne(id);
    }

    @Override
    public Iterable<MailAddress> findAll() {
        return mailAddressRepository.findAll();
    }

    @Override
    public MailAddress find(MailAddress mailAddress) {
        return mailAddressRepository.findOne(mailAddress.getId());
    }

    @Override
    public Set<MailAddress> findAllByOwner() {
        return mailAddressRepository.findAllByOwner(securityService.getRegisteredUser());
    }

    @Override
    public MailAddress add(MailAddress mailAddress) {
        return mailAddressRepository.save(mailAddress);
    }

    @Override
    public Iterable<MailAddress> addAll(Iterable<MailAddress> iterable) {
        return mailAddressRepository.save(iterable);
    }

    @Override
    public MailAddress update(MailAddress mailAddress) {
        return mailAddressRepository.save(mailAddress);
    }

    @Override
    public Iterable<MailAddress> updateAll(Iterable<MailAddress> iterable) {
         return mailAddressRepository.save(iterable);
    }

    @Override
    public void delete(MailAddress mailAddress) {
        mailAddressRepository.delete(mailAddress);
    }

    @Override
    public void deleteAll() {
        mailAddressRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        mailAddressRepository.delete(id);
    }

    @Override
    public void deleteById(Iterable<MailAddress> iterable) {
        mailAddressRepository.delete(iterable);
    }
}

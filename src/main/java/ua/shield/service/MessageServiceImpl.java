package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.GroupMailServer;
import ua.shield.entity.Message;
import ua.shield.entity.User;
import ua.shield.service.repository.MailServerRepository;
import ua.shield.service.repository.MessageRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Message findById(int id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Iterable<Message> findAll() {
        return null;
    }

    @Override
    public Message find(Message entity) {
        return null;
    }

    @Override
    public Set<Message> findAllByOwner() {
        return messageRepository.findAllByOwner(securityService.getRegisteredUser());
    }


    @Override
    public Message add(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public Iterable<Message> addAll(Iterable<Message> iterable) {
        return null;
    }

    @Override
    public Message update(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public Iterable<Message> updateAll(Iterable<Message> iterable) {
        return null;
    }

    @Override
    public void delete(Message entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteById(Iterable<Message> iterable) {

    }


}

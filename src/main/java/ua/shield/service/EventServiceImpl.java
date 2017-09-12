package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.ExtScheduleEvent;
import ua.shield.service.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 11.09.17.
 */
@Service("eventService")
public class EventServiceImpl implements EventService{
    @Autowired
    EventRepository eventRepository;

    @Autowired
    SecurityService securityService;

    @Override
    public ExtScheduleEvent findById(int id) {
        return null;
    }

    @Override
    public Iterable<ExtScheduleEvent> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public ExtScheduleEvent find(ExtScheduleEvent entity) {
        return null;
    }

    @Override
    public Set<ExtScheduleEvent> findAllByOwner() {
        return eventRepository.findAllByOwner(securityService.getRegisteredUser());
    }

    @Override
    public List<ExtScheduleEvent> findAllInTimeAndEditable() {
       //return eventRepository.findAllInTimeAndEditable(LocalDateTime.now(),true);
        return null;
    }

    @Override
    public ExtScheduleEvent add(ExtScheduleEvent entity) {
        return eventRepository.save(entity);
    }

    @Override
    public Iterable<ExtScheduleEvent> addAll(Iterable<ExtScheduleEvent> iterable) {
        return null;
    }

    @Override
    public ExtScheduleEvent update(ExtScheduleEvent entity) {
        return eventRepository.save(entity);
    }

    @Override
    public Iterable<ExtScheduleEvent> updateAll(Iterable<ExtScheduleEvent> iterable) {
        return null;
    }

    @Override
    public void delete(ExtScheduleEvent entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteById(Iterable<ExtScheduleEvent> iterable) {

    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


}

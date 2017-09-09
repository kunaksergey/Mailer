package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.shield.entity.Schedule;
import ua.shield.service.repository.ScheduleRepository;

import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Schedule findById(int id) {
        return null;
    }

    @Override
    public Iterable<Schedule> findAll() {
        return null;
    }

    @Override
    public Schedule find(Schedule entity) {
        return null;
    }

    @Override
    public Set<Schedule> findAllByOwner() {
        return scheduleRepository.findAllByOwner(securityService.getRegisteredUser());
    }




    @Override
    public Schedule add(Schedule entity) {
        return null;
    }

    @Override
    public Iterable<Schedule> addAll(Iterable<Schedule> iterable) {
        return null;
    }

    @Override
    public Schedule update(Schedule entity) {
        return null;
    }

    @Override
    public Iterable<Schedule> updateAll(Iterable<Schedule> iterable) {
        return null;
    }

    @Override
    public void delete(Schedule entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteById(Iterable<Schedule> iterable) {

    }
}

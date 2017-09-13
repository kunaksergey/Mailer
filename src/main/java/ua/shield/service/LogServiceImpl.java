package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Log;
import ua.shield.service.repository.LogRespository;

import java.util.Set;

/**
 * Created by sa on 12.09.17.
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired
    LogRespository logRespository;

    @Autowired
    SecurityService securityService;

    @Override
    public Log findById(int id) {
        return null;
    }

    @Override
    public Iterable<Log> findAll() {
        return null;
    }

    @Override
    public Log find(Log entity) {
        return null;
    }

    @Override
    public Set<Log> findAllByOwner() {
        return null;
    }

    @Override
    public Log add(Log entity) {
        return logRespository.save(entity);
    }

    @Override
    public Iterable<Log> addAll(Iterable<Log> iterable) {
        return null;
    }

    @Override
    public Log update(Log entity) {
        return null;
    }

    @Override
    public Iterable<Log> updateAll(Iterable<Log> iterable) {
        return null;
    }

    @Override
    public void delete(Log entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteById(Iterable<Log> iterable) {

    }
}

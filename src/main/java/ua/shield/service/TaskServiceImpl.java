package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Task;
import ua.shield.service.repository.TaskRepository;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by sa on 01.09.17.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService,Serializable {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Task findById(int id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task find(Task task) {
        return taskRepository.findOne(task.getId());
    }

    @Override
    public Set<Task> findAllByOwner() {
        return taskRepository.findAllByOwner(securityService.getRegisteredUser());

    }

    @Override
    public Task add(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> addAll(Iterable<Task> iterable) {
        return taskRepository.save(iterable);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> updateAll(Iterable<Task> iterable) {
        return taskRepository.save(iterable);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        taskRepository.delete(id);
    }

    @Override
    public void deleteById(Iterable<Task> iterable) {
        taskRepository.delete(iterable);
    }


}

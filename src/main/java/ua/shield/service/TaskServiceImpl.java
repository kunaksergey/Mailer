package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Schedule;
import ua.shield.entity.Task;
import ua.shield.entity.User;
import ua.shield.service.repository.TaskRepository;

import java.util.*;

/**
 * Created by sa on 01.09.17.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService{

    List<Task> listTask= Arrays.asList(
            new Task(0,"One task"),
            new Task(1,"Two task"),
            new Task(2,"Three task"),
            new Task(3,"For task")
            );
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Task findById(int id){
        //return taskRepository.findOne(id);
        return listTask.get(id);
    }

    @Override
    public Iterable<Task> findAll(){
        return taskRepository.findAll();
    }

    @Override
    public Task find(Task task){
        return taskRepository.findOne(task.getId());
    }

    @Override
    public Set<Task> findAllByOwner() {
        //return (Set<Task>) taskRepository.findOne(securityService.getRegisteredUser().getId());
        return new LinkedHashSet<>(listTask);
    }

    @Override
    public Task add(Task task){
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> addAll(Iterable<Task> iterable) {
         return taskRepository.save(iterable);
    }

    @Override
    public Task update(Task task){
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> updateAll(Iterable<Task> iterable) {
        return taskRepository.save(iterable);
    }

    @Override
    public void delete(Task task){
        taskRepository.delete(task);
    }

    @Override
    public void deleteAll(){
         taskRepository.deleteAll();
    }

    @Override
    public void deleteById(int id){
        taskRepository.delete(id);
    }

    @Override
    public void deleteById(Iterable<Task> iterable){
        taskRepository.delete(iterable);
    }


}

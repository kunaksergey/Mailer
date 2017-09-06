package ua.shield.jsf.controller;

import ua.shield.entity.Schedule;
import ua.shield.service.ScheduleService;
import ua.shield.service.SecurityServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
@ManagedBean
@SessionScoped
public class ScheduleJsfController {

    Schedule schedule;

    @ManagedProperty("#{securityService}")
    private SecurityServiceImpl securityService;

    @ManagedProperty("#{scheduleService}")
    ScheduleService service;

    public Set<Schedule> mailAddressSet() {
        return service.findAllByOwner();
    }
}

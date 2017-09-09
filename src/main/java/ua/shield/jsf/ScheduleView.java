package ua.shield.jsf;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import ua.shield.entity.ExtScheduleEvent;
import ua.shield.entity.Task;
import ua.shield.enumer.DateStrategy;
import ua.shield.exeption.WrongFillDateException;
import ua.shield.helper.FrontMessage;
import ua.shield.helper.calcDate.*;
import ua.shield.jsf.controller.TaskJsfController;
import ua.shield.service.IService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by sa on 02.09.17.
 */
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {
    private ScheduleModel eventModel;
    private ExtScheduleEvent event;
    private IRunDateStategy runDateStategy;
    private Set<Task> tasks;
    private Map<DateStrategy,IRunDateStategy> dateStategyMap;

    @ManagedProperty("#{taskJsfController}")
    private TaskJsfController taskJsfController;

    @ManagedProperty("#{taskService}")
    private IService<Task> service;

    @PostConstruct
    public void init() {
        event = new ExtScheduleEvent();
        eventModel = new DefaultScheduleModel();
        eventModel.addEvent(new ExtScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
        eventModel.addEvent(new ExtScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
        eventModel.addEvent(new ExtScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
        eventModel.addEvent(new ExtScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));

        dateStategyMap=new HashMap<>();
        dateStategyMap.put(DateStrategy.ONCE,new OnceRunDateStategy());
        dateStategyMap.put(DateStrategy.EVERYHOUR,new EveryHourRunDateStategy());
        dateStategyMap.put(DateStrategy.EVERYDAY,new EveryDayRunDateStategy());
        dateStategyMap.put(DateStrategy.EVERYWEEK,new EveryWeekRunDateStategy());
        dateStategyMap.put(DateStrategy.SPECIALPERIOD,new SpecialPeriodRunDateStategy());
        tasks=service.findAllByOwner();
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }


    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ExtScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ExtScheduleEvent event) {
        this.event = event;
    }

    public TaskJsfController getTaskJsfController() {
        return taskJsfController;
    }

    public void setTaskJsfController(TaskJsfController taskJsfController) {
        this.taskJsfController = taskJsfController;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public IService<Task> getService() {
        return service;
    }

    public void setService(IService<Task> service) {
        this.service = service;
    }



    public void addEvent(ActionEvent actionEvent) {
        try {
            checkFillDate(event);
            calcNextRunDate(event);
            if (event.getId() == null)
                eventModel.addEvent(event);
            else
                eventModel.updateEvent(event);
        } catch (WrongFillDateException e) {
            FrontMessage.addMessage(e.getMessage());
        }
   }

    //проверяем коррекстность заполнения дат
    private void checkFillDate(ExtScheduleEvent event) throws WrongFillDateException {
        LocalDateTime ldtStart = LocalDateTime.ofInstant(event.getStartDate().toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtNow=LocalDateTime.now();
        //проверяем время начала
        if(ldtStart.isBefore(ldtNow)){
            throw new WrongFillDateException("You can't create event earlier now");
        }

        //если у нас нет диапазона, то время начала=времени конца
        if(event.getDateStrategy()== DateStrategy.ONCE){
            event.setEndDate(event.getStartDate());
        }

        if(event.getDateStrategy()!= DateStrategy.ONCE){
            /*Корректируем время*/
            LocalDateTime ldtEnd = LocalDateTime.ofInstant(event.getEndDate().toInstant(), ZoneId.systemDefault());
            LocalDateTime ldtCorrectEnd = LocalDateTime.of(ldtEnd.toLocalDate(), ldtStart.toLocalTime());
            Instant instant = ldtCorrectEnd.atZone(ZoneId.systemDefault()).toInstant();
            event.setEndDate(Date.from(instant));
            /****************************/


            //разница между датами в днях
            long diffDays = ChronoUnit.DAYS.between(ldtStart, ldtCorrectEnd);

            if(diffDays<1){
                throw new WrongFillDateException("Wrong fill date");
            }
        }

    }

    //рассчитываем дату слдеующего запуска
    public void calcNextRunDate(ExtScheduleEvent event){
        Date date=(event.getNextRunDate()!=null)?event.getNextRunDate():event.getStartDate();
        IRunDateStategy dateStategy = dateStategyMap.get(event.getDateStrategy());
        dateStategy.setDays(event.getDay());
        dateStategy.setHours(event.getHour());
        event.setNextRunDate(dateStategy.nextRunDate(date));
    }

    //обрабатываем событие с фронта
    public void onEventChangeDateStrategy() {
        DateStrategy dateStrategy = event.getDateStrategy();
        IRunDateStategy iRunDateStategy = dateStategyMap.get(event.getDateStrategy());
        event.setDay(iRunDateStategy.getDays());
        event.setHour(iRunDateStategy.getHours());
   }



    public void onEventSelect(SelectEvent selectEvent) {
        event = (ExtScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new ExtScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public IRunDateStategy getRunDateStategy() {
        return runDateStategy;
    }

    public void setRunDateStategy(IRunDateStategy runDateStategy) {
        this.runDateStategy = runDateStategy;
    }
}

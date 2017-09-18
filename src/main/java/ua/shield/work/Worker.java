package ua.shield.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;
import ua.shield.entity.ExtScheduleEvent;
import ua.shield.entity.Log;
import ua.shield.enumer.DateStrategy;
import ua.shield.helper.calcDate.RunDayStategy;
import ua.shield.service.EventService;
import ua.shield.service.LogService;
import ua.shield.service.MailService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sa on 13.09.17.
 */
@Component
public class Worker {

    @Autowired
    private EventService eventService;

    @Autowired
    private MailService mailService;

    @Autowired
    private LogService logService;

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    // run work right now
    public void work() {
        work(LocalDateTime.now());
    }

    // run work right in particular time
    public void work(LocalDateTime localDateTime) {
        //get all events that has nextRunDate less then now
        List<ExtScheduleEvent> eventList = eventService.findAllLessThanTimeAndEditable(localDateTime, true);

        //future result
        List<Future<Log>> futureLogList = new ArrayList<>();

        //run all event in owner thread
        for (ExtScheduleEvent event : eventList) {
            futureLogList.add(mailService.sendEvent(event));
            checkEventForNextRun(event);
            //save event
            eventService.update(event);
        }

        if (futureLogList.size() > 0) {
            saveLog(futureLogList);
        }
    }

    private void checkEventForNextRun(ExtScheduleEvent event) {
        //nexRunDateDb from DB
        Date nexRunDateDb = event.getNextRunDate();

        //run day stategy
        RunDayStategy runDayStategy = new RunDayStategy(event.getDay(), event.getHour());
        Date nextRunDate = runDayStategy.nextRunDate(nexRunDateDb);

        // (next run date > current run date or it's one then event becames not editable
        if (nextRunDate.after(event.getEndDate()) || event.getDateStrategy() == DateStrategy.ONCE) {
            event.setEditable(false);
        } else {
            event.setNextRunDate(nextRunDate);
        }
    }

    //save future result in database
    private void saveLog(List<Future<Log>> futureList) {
        futureList.forEach(f -> {
            try {
                logService.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

package ua.shield.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.shield.entity.ExtScheduleEvent;
import ua.shield.entity.Log;
import ua.shield.service.EventService;
import ua.shield.service.LogService;
import ua.shield.service.MailService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sa on 13.09.17.
 */
@Component
public class Woker {

    @Autowired
    private EventService eventService;

    @Autowired
    private MailService mailService;

    @Autowired
    private LogService logService;

    //
    public void work() {
        //get all events
        List<ExtScheduleEvent> eventList = eventService.findAllLessThanTimeAndEditable(LocalDateTime.now(), true);

        //future result
        List<Future<Log>> futureList = new ArrayList<>();

        //run all event in owner thread
        for (ExtScheduleEvent event : eventList) {
            futureList.add(mailService.sendEvent(event));
        }

        //save future result in database
        futureList.forEach(f -> {
            try {
                logService.add(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });


    }
}

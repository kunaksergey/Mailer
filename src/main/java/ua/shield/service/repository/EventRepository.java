package ua.shield.service.repository;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.ExtScheduleEvent;
import ua.shield.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 11.09.17.
 */
public interface EventRepository extends CrudRepository<ExtScheduleEvent, Integer> {

   //@Query("select event FROM extScheduleEvent event where  event.nextRunDate<=:nextRunDate and event.editable=:editable")
   List<ExtScheduleEvent> findAllByNextRunDateLessThanEqualAndEditable(LocalDateTime nextRunDate, boolean editable);

   Set<ExtScheduleEvent> findAllByOwner(User owner);

}

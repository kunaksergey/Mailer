package ua.shield.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by sa on 11.09.17.
 */
public class ConverterDateAndLocalDateTime {
    public static Date LocalDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime DateToLocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}

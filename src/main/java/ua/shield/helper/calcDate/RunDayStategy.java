package ua.shield.helper.calcDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by sa on 08.09.17.
 */
abstract public class RunDayStategy implements IRunDateStategy {
    private int hours;
    private int days;


    public RunDayStategy(int days, int hours) {
        this.hours = hours;
        this.days = days;
    }

    public Date nextRunDate(Date date) {
        LocalDateTime ldtDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        ldtDate.plusHours(hours);
        ldtDate.plusDays(days);
        Instant instant = ldtDate.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

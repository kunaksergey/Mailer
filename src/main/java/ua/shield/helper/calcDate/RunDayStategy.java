package ua.shield.helper.calcDate;

import ua.shield.helper.ConverterDateAndLocalDateTime;

import java.time.LocalDateTime;
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
        LocalDateTime ldtDate = ConverterDateAndLocalDateTime.DateToLocalDateTime(date);
        ldtDate.plusHours(hours);
        ldtDate.plusDays(days);
        return ConverterDateAndLocalDateTime.LocalDateTimeToDate(ldtDate);
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

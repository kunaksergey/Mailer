package ua.shield.helper.calcDate;

import java.util.Date;

/**
 * Created by sa on 08.09.17.
 */
public interface IRunDateStategy {
    Date nextRunDate(Date date);
    int getHours();
    void setHours(int hours); ;
    int getDays();;
    void setDays(int days);
}

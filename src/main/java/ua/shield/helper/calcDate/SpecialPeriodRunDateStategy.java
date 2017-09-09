package ua.shield.helper.calcDate;

/**
 * Created by sa on 08.09.17.
 */
//every day in the same time
public class SpecialPeriodRunDateStategy extends RunDayStategy {


    public SpecialPeriodRunDateStategy(int rangeDay, int rangeHour) {
        super(rangeDay, rangeHour);
    }

    public SpecialPeriodRunDateStategy() {
        super(0, 0);
    }
}

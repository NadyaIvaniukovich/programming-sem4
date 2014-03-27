package by.bsu.ivanyukovich.entertainment.criteria;

import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;
import by.bsu.ivanyukovich.entertainment.place.WorkTimeLimited;

/**
 * Created by Hope on 3/26/14.
 */
public class WorkTimeLimitCriteria implements Criteria{
    private int openingTime;
    private int closingTime;

    public WorkTimeLimitCriteria(int openingTime, int closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    @Override
    public int getPriority(EntertainmentPlace object) {
        if(object instanceof WorkTimeLimited){
            WorkTimeLimited place = (WorkTimeLimited)object;
            return (place.getOpeningTime() < openingTime && closingTime < place.getClosingTime()) ? 1 : 0;
        }
        return 1;
    }
}

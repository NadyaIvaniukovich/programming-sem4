package by.bsu.ivanyukovich.entertainment.criteria;

import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;

/**
 * Created by Hope on 3/26/14.
 */
public class StayTimeLimitedCriteria implements Criteria {

    private boolean stayTimeLimited;

    public StayTimeLimitedCriteria(boolean stayTimeLimited) {
        this.stayTimeLimited = stayTimeLimited;
    }

    @Override
    public int getPriority(EntertainmentPlace object) {
        return object.isStayTimeLimited() && stayTimeLimited ? 1 : 0;
    }
}

package by.bsu.ivanyukovich.entertainment.criteria;

import by.bsu.ivanyukovich.entertainment.place.AgeLimited;
import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;

/**
 * Created by Hope on 3/26/14.
 */
public class AgeLimitCriteria implements Criteria {
    private int ageLimit;

    public AgeLimitCriteria(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public int getPriority(EntertainmentPlace object) {
        if(object instanceof AgeLimited){
            AgeLimited place = (AgeLimited)object;
            return ageLimit < place.getAgeLimit() ? 0 : 1;
        }
        return 1;
    }
}

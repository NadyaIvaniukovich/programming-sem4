package by.bsu.ivanyukovich.entertainment.criteria;

import by.bsu.ivanyukovich.entertainment.place.CompanionType;
import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;

import java.util.List;

/**
 * Created by Hope on 3/26/14.
 */
public class CompanionsCriteria implements Criteria {
    private List<CompanionType> companions;

    public CompanionsCriteria(List<CompanionType> companions) {
        this.companions = companions;
    }

    @Override
    public int getPriority(EntertainmentPlace place) {
        int count = 0;
        for (CompanionType type: companions){
            if(place.getCompanions().indexOf(type) > -1){
                count++;
            }
        }
        return count;
    }

    public List<CompanionType> getCompanions() {
        return companions;
    }
}

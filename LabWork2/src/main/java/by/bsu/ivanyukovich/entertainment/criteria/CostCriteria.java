package by.bsu.ivanyukovich.entertainment.criteria;

import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;
import by.bsu.ivanyukovich.entertainment.place.Park;
import by.bsu.ivanyukovich.entertainment.place.Payable;

/**
 * Created by Hope on 3/26/14.
 */
public class CostCriteria implements Criteria{
    private int cost;

    public CostCriteria(int cost) {
        this.cost = cost;
    }

    @Override
    public int getPriority(EntertainmentPlace object) {
        if(object instanceof Payable){
            Payable place = (Payable)object;
            return place.getCost() > cost ? 0 : 1;
        }
        return 1;
    }
}

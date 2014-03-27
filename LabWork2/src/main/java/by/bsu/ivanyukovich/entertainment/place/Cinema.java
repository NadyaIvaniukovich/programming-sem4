package by.bsu.ivanyukovich.entertainment.place;

import java.util.List;

/**
 * Created by Hope on 3/25/14.
 */
public class Cinema extends EntertainmentPlace implements Payable, WorkTimeLimited, AgeLimited {
    protected int cost;
    protected int ageLimit;
    protected int openingTime;
    protected int closingTime;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }
}

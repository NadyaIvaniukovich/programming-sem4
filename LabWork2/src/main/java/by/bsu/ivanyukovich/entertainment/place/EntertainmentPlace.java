package by.bsu.ivanyukovich.entertainment.place;

import java.util.List;

/**
 * Created by Hope on 3/25/14.
 */
public abstract class EntertainmentPlace{
    protected String name;
    protected boolean stayTimeLimited;
    protected List<CompanionType> companions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStayTimeLimited() {
        return stayTimeLimited;
    }

    public void setStayTimeLimited(boolean stayTimeLimited) {
        this.stayTimeLimited = stayTimeLimited;
    }

    public List<CompanionType> getCompanions() {
        return companions;
    }

    public void setCompanions(List<CompanionType> companions) {
        this.companions = companions;
    }

    @Override
    public String toString() {
        return name;
    }
}

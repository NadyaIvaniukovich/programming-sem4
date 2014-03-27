package by.bsu.ivanyukovich.entertainment.place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hope on 3/26/14.
 */
public enum CompanionType {
    FRIENDS, FAMILY, CHILDREN, PARTNER, COLLEAGUES;

    public static List<CompanionType> parseCompanions(String companionsString){
        List<CompanionType> companions = new ArrayList<>();
        for (String companionString: companionsString.split(" ")){
            companions.add(CompanionType.valueOf(companionString));
        }
        return companions;
    }

}

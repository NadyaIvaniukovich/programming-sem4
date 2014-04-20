package by.bsu.ivanyukovich.entertainment;

import by.bsu.ivanyukovich.entertainment.criteria.InvalidCriteriaException;
import by.bsu.ivanyukovich.entertainment.io.CriteriaReader;
import by.bsu.ivanyukovich.entertainment.io.EntertainmentPlaceReader;
import by.bsu.ivanyukovich.entertainment.place.Cinema;
import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;
import by.bsu.ivanyukovich.entertainment.place.InvalidEntertainmentPlaceException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Hope on 3/25/14.
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        try {
            EntertainmentPlaceReader placeReader = new EntertainmentPlaceReader("src/main/resources/places.csv");
            CriteriaReader criteriaReader = new CriteriaReader("src/main/resources/request1.csv");
            EntertainmentFinder entertainmentFinder = new EntertainmentFinder(placeReader.read());
            log.info("Most suitable:" + entertainmentFinder.search(criteriaReader.read(), 3));
        } catch (InvalidCriteriaException | InvalidEntertainmentPlaceException e){
            log.log(Level.SEVERE, e.getMessage(), e);
        }

    }

}

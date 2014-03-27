package by.bsu.ivanyukovich.entertainment;

import by.bsu.ivanyukovich.entertainment.io.CriteriaReader;
import by.bsu.ivanyukovich.entertainment.io.EntertainmentPlaceReader;
import by.bsu.ivanyukovich.entertainment.place.Cinema;
import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;

import java.util.List;

/**
 * Created by Hope on 3/25/14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        EntertainmentPlaceReader placeReader = new EntertainmentPlaceReader("src/main/resources/places.csv");
        CriteriaReader criteriaReader = new CriteriaReader("src/main/resources/request1.csv");
        EntertainmentFinder entertainmentFinder = new EntertainmentFinder(placeReader.read());
        System.out.println("Most suitable:" + entertainmentFinder.search(criteriaReader.read(),3));

    }

}

package by.bsu.ivanyukovich.entertainment.io;

import by.bsu.ivanyukovich.entertainment.place.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.bsu.ivanyukovich.entertainment.place.CompanionType.parseCompanions;

/**
 * Created by Hope on 3/25/14.
 */
public class EntertainmentPlaceReader {
    private static final int DAY = 24;

    private String fileName;

    public EntertainmentPlaceReader(String fileName) {
        this.fileName = fileName;
    }

    public List<EntertainmentPlace> read() throws IOException {
        try(FileInputStream in = new FileInputStream(fileName);
            Scanner scanner = new Scanner(in)
        ){
            List<EntertainmentPlace> places = new ArrayList<>();
            while(scanner.hasNext()){
                String[] row = scanner.nextLine().split(",");
                switch (row[0]){
                    case "Cinema": places.add(parseCinema(row)); break;
                    case "Circus": places.add(parseCircus(row)); break;
                    case "Club": places.add(parseClub(row)); break;
                    case "Museum": places.add(parseMuseum(row)); break;
                    case "Park": places.add(parsePark(row)); break;
                    case "Restaurant": places.add(parseRestaurant(row)); break;
                }

            }
            return places;
        }
    }
    private Cinema parseCinema(String... properties){
        Cinema cinema = new Cinema();
        cinema.setName(properties[1]);
        cinema.setStayTimeLimited(properties[2].equals("true"));
        cinema.setCompanions(parseCompanions(properties[3]));
        cinema.setCost(Integer.parseInt(properties[4]));
        cinema.setAgeLimit(Integer.parseInt(properties[5]));
        cinema.setOpeningTime(Integer.parseInt(properties[6]));
        cinema.setClosingTime(Integer.parseInt(properties[7]));
        return cinema;
    }

    private Circus parseCircus(String... properties){
        Circus circus = new Circus();
        circus.setName(properties[1]);
        circus.setStayTimeLimited(properties[2].equals("true"));
        circus.setCompanions(parseCompanions(properties[3]));
        circus.setCost(Integer.parseInt(properties[4]));
        circus.setOpeningTime(Integer.parseInt(properties[6]));
        circus.setClosingTime(Integer.parseInt(properties[7]));
        return circus;
    }

    private Club parseClub(String... properties){
        Club club = new Club();
        club.setName(properties[1]);
        club.setStayTimeLimited(properties[2].equals("true"));
        club.setCompanions(parseCompanions(properties[3]));
        club.setCost(Integer.parseInt(properties[4]));
        club.setAgeLimit(Integer.parseInt(properties[5]));
        club.setOpeningTime(Integer.parseInt(properties[6]));
        club.setClosingTime(Integer.parseInt(properties[7]) + DAY); //We assume that club is closing after midnight
        return club;
    }

    private Museum parseMuseum(String... properties){
        Museum museum = new Museum();
        museum.setName(properties[1]);
        museum.setStayTimeLimited(properties[2].equals("true"));
        museum.setCompanions(parseCompanions(properties[3]));
        museum.setCost(Integer.parseInt(properties[4]));
        museum.setAgeLimit(Integer.parseInt(properties[5]));
        museum.setOpeningTime(Integer.parseInt(properties[6]));
        museum.setClosingTime(Integer.parseInt(properties[7]));
        return museum;
    }

    private Park parsePark(String... properties){
        Park park = new Park();
        park.setName(properties[1]);
        park.setStayTimeLimited(properties[2].equals("true"));
        park.setCompanions(parseCompanions(properties[3]));
        return park;
    }

    private Restaurant parseRestaurant(String... properties){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(properties[1]);
        restaurant.setStayTimeLimited(properties[2].equals("true"));
        restaurant.setCompanions(parseCompanions(properties[3]));
        restaurant.setCost(Integer.parseInt(properties[4]));
        restaurant.setOpeningTime(Integer.parseInt(properties[6]));
        restaurant.setClosingTime(Integer.parseInt(properties[7]));
        return restaurant;
    }


}

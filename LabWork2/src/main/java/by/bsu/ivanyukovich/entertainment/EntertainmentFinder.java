package by.bsu.ivanyukovich.entertainment;

import by.bsu.ivanyukovich.entertainment.criteria.Criteria;
import by.bsu.ivanyukovich.entertainment.place.EntertainmentPlace;

import java.util.*;

/**
 * Created by Hope on 3/26/14.
 */
public class EntertainmentFinder {
    private List<EntertainmentPlace> places;

    public EntertainmentFinder(List<EntertainmentPlace> places) {
        this.places = places;
    }

    public List<EntertainmentPlace> search(List<Criteria> criteriaList, int resultCount){
        SortedMap<Integer,List<EntertainmentPlace>> prioritizedPlaces = new TreeMap<>();
        for (EntertainmentPlace place : places){
            int priority = 0;
            for (Criteria criteria: criteriaList){
               priority += criteria.getPriority(place);
            }
            List<EntertainmentPlace> placeList = prioritizedPlaces.get(priority);
            if(placeList == null){
                placeList = new ArrayList<>();
                prioritizedPlaces.put(priority, placeList);
            }
            placeList.add(place);
        }
        //System.out.println(prioritizedPlaces);
        List<EntertainmentPlace> result = new ArrayList<>();
        while (resultCount > 0){
            Integer lastKey = prioritizedPlaces.lastKey();
            List<EntertainmentPlace> placeList = prioritizedPlaces.get(lastKey);
            if(placeList.size() <= resultCount){
                result.addAll(placeList);
                resultCount -= placeList.size();
            } else {
                result.addAll(placeList.subList(0, resultCount));
                break;
            }
        }
        return result;
    }

}
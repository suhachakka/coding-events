package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {
    private static Map<Integer, Event> events = new HashMap<>();
    //private static ArrayList<Event> events = new ArrayList<>();
    public static Collection<Event> getAll() {

        return events.values();
        //return events;


    }

    public static void add(Event event) {

        events.put(event.getId(), event);
        //events.add(event);
    }

    public static Event getById(Integer id) {
        return events.get(id);
    }
   /* public static Event getById(int id) {
        for(Event event : events) {
            if (event.getId() == id)
                return event;

        }
        return null;

    }*/

    public static void remove(Integer id) {
        if (events.containsKey(id)) {
            events.remove(id);
        }
    }
//    public static Event edit(int id,String name,String description,String contactEmail,Integer Attendees){
//        Event eventToEdit = getById(id);
//        if(events.equals(eventToEdit)){
//            Event event = new Event(name, description,contactEmail,Attendees);
//            return event;
//        }
//        return eventToEdit;
//    }
}

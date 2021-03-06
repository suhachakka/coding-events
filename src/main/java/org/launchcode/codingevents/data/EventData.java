package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {
    private static Map<Integer, Event> events = new HashMap<>();
    public static Collection<Event> getAll() {
        return events.values();
    }

//    public static void add(Event event) {
//        events.put(event.getId(), event);
//    }

    public static Event getById(Integer id) {
        return events.get(id);
    }
  public static void addEvent(Event event) {
      Integer eventId = event.getId();
      if( !events.containsKey(eventId)){
          events.put(eventId, event);
      }
  }
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
    public static void edit(Integer id, Event event){
        Integer eventEdit = event.getId();
        if(events.containsKey(eventEdit)){
            events.put(eventEdit,event);
        }

    }
}

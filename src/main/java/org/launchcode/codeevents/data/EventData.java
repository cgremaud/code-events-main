package org.launchcode.codeevents.data;

import org.launchcode.codeevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

    //A place to put events
    private static final Map<Integer, Event> events = new HashMap<>();

    //get all events
    public static Collection<Event> getAll(){
        return events.values();
    }

    //get single event
    public static Event getById(int id) {
        return events.get(id);
    }

    //add an event
    public static void add(Event event){
        events.put(event.getId(), event);
    }

    //remove an event
    public static void remove(int id) {
        events.remove(id);
    }
}

package org.launchcode.codeevents.controllers;


import org.launchcode.codeevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

//    private static List<HashMap<String, String>>eventsList = new ArrayList<>(); //Should be a list of HashMaps though?
    private static List<Event> eventsList = new ArrayList<>();

//    private static HashMap<String, String> event1 = new HashMap<>();

//    public static void initializeEventsList() {
////        event1.put("name", "WWDC");
////        event1.put("description", "Apple's Wordwide Dev Conf");
////        eventsList.add();
//
//    }

    @GetMapping
    public String index (Model model) {

//        HashMap<String, String> event1 = new HashMap<>();
        //TODO this is causing an issue because it's being called and added every time a request is made to /events
        //no clue how to get it to call ONCE and then leave eventsList alone. . .
        //The solution will probably involve the M bit of MVC
//        initializeEventsList();


        String title = "Events List: All";
        model.addAttribute("title", title);
        model.addAttribute("eventsList", eventsList);
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        String title = "Create Event";
        return "events/create";
    }

    @PostMapping("create")
    public String generateNewEvent(@RequestParam String name, @RequestParam String description) {
//        HashMap<String, String> event = new HashMap<>();
//        event.put("name", name);
//        event.put("description", description);
        Event event = new Event(name, description);
        eventsList.add(event);

        return "redirect:";
    }
}

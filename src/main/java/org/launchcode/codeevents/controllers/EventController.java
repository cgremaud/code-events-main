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
        Event event = new Event(name, description);
        eventsList.add(event);

        return "redirect:";
    }
}

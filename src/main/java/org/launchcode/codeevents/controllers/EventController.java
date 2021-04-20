package org.launchcode.codeevents.controllers;


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

    private static List<HashMap<String, String>> eventsList = new ArrayList<>(); //Should be a list of HashMaps though?
    @GetMapping
    public String index (Model model) {

        HashMap<String, String> event1 = new HashMap<>();

        event1.put("name", "WWDC");
        event1.put("description", "Apples Word Wide Developer Conference");
        eventsList.add(event1);


        String title = "Events List: All";
        model.addAttribute("title", title);
        model.addAttribute("eventsList", eventsList);
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {

        return "events/create";
    }

    @PostMapping("create")
    public String generateNewEvent(@RequestParam String name, @RequestParam String description) {
        HashMap<String, String> event = new HashMap<>();
        event.put("name", name);
        event.put("description", description);
        eventsList.add(event);

        return "redirect:"; //might need to return redirect: here. Not sure
    }
}

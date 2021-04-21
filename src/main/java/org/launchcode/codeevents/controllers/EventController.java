package org.launchcode.codeevents.controllers;


import org.launchcode.codeevents.data.EventData;
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


    @GetMapping
    public String index (Model model) {
        String title = "Events List: All";
        model.addAttribute("title", title);
        model.addAttribute("events", EventData.getAll());

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
        EventData.add(event);

        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam int[] eventIds) {
        for (int id : eventIds) {
            EventData.remove(id);
        }
        return "redirect:";
    }
}

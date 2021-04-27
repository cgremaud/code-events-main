package org.launchcode.codeevents.controllers;


import org.launchcode.codeevents.data.EventData;
import org.launchcode.codeevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
    public String generateNewEvent(@ModelAttribute  @Valid Event newEvent, Errors errors, Model model) { //TODO Ok so it's "working" but IntelliJ doesn't like it for some reason
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad data!");
            return "events/create";
        }
        EventData.add(newEvent);

        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if(eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{id}")
    public String displayEditForm(Model model, @PathVariable int id) {
        model.addAttribute("event", EventData.getById(id));
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(@RequestParam int eventId, @RequestParam String name, @RequestParam String description){
        Event eventToEdit = EventData.getById(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:";
    }
}

package org.launchcode.codeevents.controllers;


import org.launchcode.codeevents.data.EventRepository;
import org.launchcode.codeevents.models.Event;
import org.launchcode.codeevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    //findAll, save, findBy

    @GetMapping
    public String index (Model model) {
        String title = "Events List: All";
        model.addAttribute("title", title);
        model.addAttribute("events", eventRepository.findAll());

        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        String title = "Create Event";
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event()); //Side effect of this is that now nextId increments every time you make a GET request to /create
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String generateNewEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad data!");
            return "events/create";
        }
       eventRepository.save(newEvent);

        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if(eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{id}")
    //it seems like it's breaking when the get request is sent,  not the post request, but i can't see anything wrong with this?
    public String displayEditForm(Model model, @PathVariable int id) {
        model.addAttribute("event", eventRepository.findById(id)); //this should just return an event that gets displayed by the edit form.
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(@RequestParam int eventId, @ ModelAttribute @Valid Event event, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad data!");
            return "redirect:";
        }
        //TODO this will break under the new persistent version. Not sure how to refactor it.
        //I can get the event to edit by using .findById(eventId), but then how to update?
        Object eventToEdit = eventRepository.findById(eventId);
        ((Event) eventToEdit).setName(event.getName());
        ((Event) eventToEdit).setDescription(event.getDescription());
        ((Event) eventToEdit).setContactEmail(event.getContactEmail());
        eventRepository.save(((Event) eventToEdit)); //Okay this might not work? But let's see.
        return "redirect:";
    }
}

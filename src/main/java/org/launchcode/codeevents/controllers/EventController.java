package org.launchcode.codeevents.controllers;


import org.launchcode.codeevents.data.EventCategoryRepository;
import org.launchcode.codeevents.data.EventRepository;
import org.launchcode.codeevents.models.Event;
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

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

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
        model.addAttribute("categories", eventCategoryRepository.findAll());
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

    @GetMapping("edit/{id}") //TODO Throws an error on any GET request to /edit. Try refactoring to use a request param?
    //it seems like it's breaking when the get request is sent,  not the post request, but i can't see anything wrong with this?
    public String displayEditForm(Model model, @PathVariable int id, @ModelAttribute Event eventToEdit) {
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
        //TODO Refactor this to work under persistent version.
        Object eventToEdit = eventRepository.findById(eventId);
        ((Event) eventToEdit).setName(event.getName()); //don't understand why this is required to satisfy intellij when eventRepository.findById should by definition return an event object.
        ((Event) eventToEdit).setDescription(event.getDescription());
        ((Event) eventToEdit).setContactEmail(event.getContactEmail());
        eventRepository.save(((Event) eventToEdit));
        return "redirect:";
    }
}

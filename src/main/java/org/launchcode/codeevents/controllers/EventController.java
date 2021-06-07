package org.launchcode.codeevents.controllers;

import org.launchcode.codeevents.data.EventCategoryRepository;
import org.launchcode.codeevents.data.EventRepository;
import org.launchcode.codeevents.models.Event;
import org.launchcode.codeevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String index (@RequestParam(required = false) Integer categoryId, Model model) {
        if (categoryId == null) {
            String title = "Events List: All";
            model.addAttribute("title", title);
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in the category " + category.getName());
                model.addAttribute("events", category.getEvents());
            }

        }
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

    @GetMapping("edit/{id}")
    public String displayEditForm(Model model, @PathVariable int id) {
        Optional<Event> result = eventRepository.findById(id);
        Event eventToEdit = result.get();
        model.addAttribute("event", eventToEdit);
        return "events/edit";
    }

    //TODO get this working again.
    @PostMapping("edit")
    public String processEditForm(@RequestParam int eventId, @ ModelAttribute @Valid Event event, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad data!");
            return "redirect:";
        }
        //TODO Refactor this to work under persistent version.
        Optional<Event> result = eventRepository.findById(eventId);

        Event eventToEdit = result.get();
        eventToEdit.setDescription(event.getDescription());
        eventToEdit.setContactEmail(event.getContactEmail());
        eventToEdit.setEventCategory(event.getEventCategory());
        eventToEdit.setName(event.getName());
        eventToEdit.setAttendees(event.getAttendees());
        eventToEdit.setLocation(event.getLocation());
        eventRepository.save(eventToEdit);
//        Event eventToEdit.setName(event.getName()); //don't understand why this is required to satisfy intellij when eventRepository.findById should by definition return an event object.
//        Event eventToEdit.setDescription(event.getDescription());
//        Event eventToEdit).setContactEmail(event.getContactEmail());
//        eventRepository.save(((Event) eventToEdit));
        return "redirect:";
    }
}

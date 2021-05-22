package org.launchcode.codeevents.controllers;

import org.launchcode.codeevents.data.EventCategoryRepository;
import org.launchcode.codeevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model) { //shouldn't this be called 'displayAllEventCategories?
        String title = "All Categories";
        model.addAttribute("title", title);
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }
    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model) {
        EventCategory newEventCategory = new EventCategory();
        model.addAttribute("title", "Create Category");

        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(Model model, Errors errors, @ModelAttribute @Valid EventCategory newEventCategory){
        if (errors.hasErrors()) {
            model.addAttribute("errorMsg", "Error! Bad data");
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:";
    }
}

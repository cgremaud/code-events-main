package org.launchcode.codeevents.controllers;

import org.launchcode.codeevents.data.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        String title = "All Categories";
        model.addAttribute("title", title);
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }
    @GetMapping("create")
    public String renderCreateEventCategoryForm() {

        return "eventCategories/create";
    }
}

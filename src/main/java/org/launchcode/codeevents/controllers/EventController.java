package org.launchcode.codeevents.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    private static List<String> eventsList = new ArrayList<>();

    @GetMapping
    public String index (Model model) {
        String title = "Events List: All";
        return "events/index";
    }

    @GetMapping("create")
    public String createEvent(Model model, @RequestParam String name) {

        return "events/create";
    }
}

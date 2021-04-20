package org.launchcode.codeevents.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("title", title);
        model.addAttribute("eventsList", eventsList);
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {

        return "events/create";
    }

    @PostMapping("create")
    public String generateNewEvent(@RequestParam String name) {
        eventsList.add(name);
        return "redirect:"; //might need to return redirect: here. Not sure
    }
//
//    @GetMapping("returnHome")
//    public String returnHome() {
//        return "index";
//    }
}

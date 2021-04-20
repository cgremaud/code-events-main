package org.launchcode.codeevents.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    public static String title = "Coding Events Homepage";
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }
}

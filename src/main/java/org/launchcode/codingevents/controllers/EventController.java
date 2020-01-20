package org.launchcode.codingevents.controllers;

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
//    private  static  List<String> events = new ArrayList();
    private static HashMap<String, String> events=new HashMap<>();
   @GetMapping
    public String displayAllEvents(Model model){
//        List<String> events = new ArrayList();
//        events.add("Code with pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platfrom");
        model.addAttribute("events",events);
         return "events/index";
    }
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }

    @PostMapping("create")
    public String displayCreateEventForm(Model model, @RequestParam String eventName,@RequestParam String description){
        events.put(eventName,description);
//        events.add(eventName);
        return "redirect:";
    }
}

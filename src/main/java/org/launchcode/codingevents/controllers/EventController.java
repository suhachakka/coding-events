package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.launchcode.codingevents.models.Event;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    //private  static  List<String> events = new ArrayList();
    //private static List<Event> events = new ArrayList<>();
//    private static HashMap<String, String> events=new HashMap<>();
    @GetMapping
    public String displayAllEvents(Model model) {
/*        List<String> events = new ArrayList();
       events.add("Code with pride");
        events.add("Strange Loop");
        events.add("Apple WWDC");
       events.add("SpringOne Platfrom");*/
        model.addAttribute("title", "All events");
        model.addAttribute("events", EventData.getAll());
//        model.addAttribute("events",events);
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create event");
        return "events/create";
    }

    //    @PostMapping("create")
/*    public String processCreateEventForm(@RequestParam String eventName,
                                        @RequestParam String eventDescription){
                   events.put(eventName,eventDescription); // Hashmap
       events.add(new Event(eventName,eventDescription)); // Model creation
            EventData.add(new Event(eventName,eventDescription)); data layer = model and data decoupling*/
//model binding
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {

        EventData.add(newEvent); // model-binding

        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        model.addAttribute("title","Edit event");
        Event eventToEdit= EventData.getById(eventId);
        model.addAttribute("events",eventToEdit);
        model.addAttribute("events", EventData.getAll());

        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId,  @RequestParam String name,
                                   @RequestParam String description,
                                  @RequestParam String contactEmail, @RequestParam Integer Attendees) {
        //EventData.edit(id,name,description,contactEmail,Attendees);
        Event event = EventData.getById(eventId);
        if (event != null) {
            event.setName(name);
            event.setDescription(description);
            event.setContactEmail(contactEmail);
            event.setAttendees(Attendees);

        }
        return "redirect:";


    }}
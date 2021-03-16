package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.codingevents.models.Event;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {
/**
  we need to use the methods from CrudRepository to get all of the instances of the Event class
*/
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;
  /*  @GetMapping
    public String displayAllEvents(Model model) {

        model.addAttribute("title", "All events");
//        model.addAttribute("events", EventData.getAll()); // passing eventdata
        model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }*/

    @GetMapping
    public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if(categoryId == null) {
            model.addAttribute("title", "All events");
            model.addAttribute("events", eventRepository.findAll());
        }else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid CategoryId: "+ categoryId);
            }else{
                EventCategory category = result.get();
                model.addAttribute("title","Events by category: "+category.getCategoryName());
                model.addAttribute("events",category.getEvents());
            }
        }
        return "events/index";
    }
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create event");
        model.addAttribute(new Event());
        //model.addAttribute("types", EventType.values()); // passing enum values
        model.addAttribute("categories",eventCategoryRepository.findAll());
        return "events/create";
    }


    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event event,Errors errors,
                                          Model model) {
       if(errors.hasErrors()){
           model.addAttribute("title","Create event");
           //model.addAttribute("types", EventType.values()); // passing enum values
           model.addAttribute("categories",eventCategoryRepository.findAll());

           return "events/create";
       }
//        EventData.add(newEvent); // model-binding
        eventRepository.save(event);
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
//        model.addAttribute("events", EventData.getAll());
        model.addAttribute("events",eventRepository.findAll());

        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
//                EventData.remove(id);
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
//        Optional<Event> event = Optional.ofNullable(eventRepository.findById(eventId).orElse(null));
        Optional<Event> event = eventRepository.findById(eventId);
        if( event.isPresent() ) {
            model.addAttribute("event", event.get());}
        //model.addAttribute("events",event);

        model.addAttribute("title","Edit event");

//        model.addAttribute("types", EventType.values()); // passing enum values
        model.addAttribute("categories",eventCategoryRepository.findAll());


        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(Event event1,@RequestParam int eventId,@RequestParam String name,
                                   @RequestParam String description,
                                 @RequestParam String contactEmail, @RequestParam Integer Attendees) {


//         event= EventData.getById(eventId)
//        Optional<Event> event = Optional.ofNullable(eventRepository.findById(eventId).orElse(null));
                Optional<Event> event = eventRepository.findById(eventId);

        if(event.isPresent())
               event1= event.get();

               event1.setName(name);
//               event1.setDescription(description);
//               event1.setContactEmail(contactEmail);
               event1.setAttendees(Attendees);
        eventRepository.save(event1);
            return "redirect:";

    }}
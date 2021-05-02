package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.*;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.*;
import java.util.List;

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

    @Autowired
    private TagRepository tagRepository;
    private Integer eventId;
    private Event event;
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

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
                                    Errors errors,
                                    Model model){

        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)){
                event.addTag(tag);
                model.addAttribute("titel","Add Tag to: " + event.getName());
                eventRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }

        return "redirect:add-tag";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }

        return "events/detail";
    }
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        /*  Optional<Event> result = eventRepository.findById(id);
        Event event = result.get();
        model.addAttribute("title","Update  Event  where  ID = "+id +" name is "+ event.getName()) ;
        model.addAttribute("event",event);
        * */
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

/*    @PostMapping("edit")
    public String processEditForm(@ModelAttribute Event event, Errors errros, @RequestParam int eventId,@RequestParam String name,
                                   EventCategory category, @Valid  EventDetails details,
                                  @RequestParam Integer Attendees) {
                                    Optional<Event> result = Optional.ofNullable(eventRepository.findById(eventId).orElse(null));
                                    event = result.get();
                                    event.setName(name);
                                    event.setAttendees(attendees);
                                    event.setEventDetails(details);
                                    eventRepository.save(event);
                                    return "redirect:";
                                    }

    */
@PostMapping("edit")
    public String processEditForm(@RequestParam(required = false) Integer eventId, @ModelAttribute Event event) {
    //event= EventData.getById(eventId)
        Optional<Event> result = Optional.ofNullable(eventRepository.findById(eventId).orElse(null));
               Event eventEdit=result.get();
               eventEdit.setName(event.getName());
               //event1.setEventCategory(category);

               eventEdit.setAttendees(event.getAttendees());
               eventEdit.setEventDetails(event.getEventDetails());
        eventRepository.save(eventEdit);
            return "redirect:";



    }
}
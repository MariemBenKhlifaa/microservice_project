package com.example.eventsservice.controller;

import com.example.eventsservice.entity.Event;
import com.example.eventsservice.service.EventInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class EventController {
    @Autowired
    EventInterface EventInterface;
    @PostMapping("/addEvent")
    public Event addEvent (@RequestBody Event e){
        return  EventInterface.AddEvent(e);
    }
    @GetMapping("/listEvent")
    public List<Event> getallEvents(){
        return  EventInterface.getallEvent();
    }
    @GetMapping("/Event/{id}")
    public Event findEvent(@PathVariable Long id){

        return EventInterface.findEvent(id);
    }
    @PutMapping("/updateEvent/{id}")
    public Event updateEvent (@RequestBody Event e,@PathVariable Long id){
        System.out.println(e);
        return EventInterface.updateEvent(e,id);
    }
    @DeleteMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable Long id){
        EventInterface.deleteEvent(id);
    }
}

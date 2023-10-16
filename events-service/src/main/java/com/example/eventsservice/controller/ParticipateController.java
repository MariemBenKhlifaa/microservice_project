package com.example.eventsservice.controller;

import com.example.eventsservice.entity.Participate;
import com.example.eventsservice.service.ParticipateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ParticipateController {
    @Autowired
    private ParticipateInterface participateInterface;
    @PostMapping("/participate/{eventId}")
    public Participate addParticipationToEvent(@PathVariable Long eventId, @RequestBody Participate participate) {
        return participateInterface.AddParticipate(eventId, participate);
    }
    @GetMapping("/listPart")
    public List<Participate> getallpart(){
        return  participateInterface.getallParticipate();
    }
    @GetMapping("/findp/{id}")

    public Participate findpart(@PathVariable Long id){

        return participateInterface.findParticipate(id);
    }

    @DeleteMapping("/deletePart/{id}")
    public void deletePart(@PathVariable Long id){
        participateInterface.deleteParticipate(id);
    }
}

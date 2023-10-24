package com.example.eventsservice.service;

import com.example.eventsservice.entity.Event;
import com.example.eventsservice.entity.Participate;
import com.example.eventsservice.repo.EventRepo;
import com.example.eventsservice.repo.ParticipateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipateService implements ParticipateInterface{
    @Autowired
    ParticipateRepo participateRepo;
    @Autowired
    private EventRepo eventRepo;
    @Override
    public List<Participate> getallParticipate() {
        return participateRepo.findAll();
    }

    @Override
    public Participate AddParticipate(Long eventId, Participate participate) {
        Event event = eventRepo.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")); // Vérifiez l'existence de l'event

        participate.setEvent(event);

        return participateRepo.save(participate);
    }

    @Override
    public Participate findParticipate(Long id) {
        return participateRepo.findById(id).get();
    }

    @Override
    public void deleteParticipate(Long id) {
        Participate participate = participateRepo.findById(id).get();
        participateRepo.delete(participate);

    }
}

package com.example.eventsservice.service;

import com.example.eventsservice.entity.Event;
import com.example.eventsservice.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventService  implements EventInterface{
    @Autowired
    EventRepo eventRepo;

    @Override
    public List<Event> getallEvent() {
        return eventRepo.findAll();
    }

    @Override
    public Event AddEvent(Event e) {
        return eventRepo.save(e);
    }

    @Override
    public Event findEvent(Long id) {
        return eventRepo.findById(id).get();
    }

    @Override
    public Event updateEvent(Event e, Long id) {
        Event event = eventRepo.findById(id).get();
        if(e.getTitre()!=null){
            event.setTitre(e.getTitre());
        }
        if (e.getDescription() !=null){
            event.setDescription(e.getDescription());
        }
        if (e.getDateEvent() !=null){
            event.setDateEvent(e.getDateEvent());
        }
        if(e.getLieu()!=null){
            event.setLieu(e.getLieu());
        }
        if (e.getPrix() !=null){
            event.setPrix(e.getPrix());
        }
        if (e.getImage() !=null){
            event.setImage(e.getImage());
        }

        return  eventRepo.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepo.findById(id).get();
        eventRepo.delete(event);

    }
}

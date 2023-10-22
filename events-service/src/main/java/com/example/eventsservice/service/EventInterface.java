package com.example.eventsservice.service;

import com.example.eventsservice.entity.Event;

import java.util.List;

public interface EventInterface {
    public List<Event> getallEvent();
    public Event AddEvent(Event e);
    public Event findEvent(Long id);
    public Event updateEvent(Event e, Long id);
    public void deleteEvent(Long id);
}

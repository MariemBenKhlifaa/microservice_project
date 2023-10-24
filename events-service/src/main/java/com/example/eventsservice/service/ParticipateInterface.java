package com.example.eventsservice.service;

import com.example.eventsservice.entity.Participate;

import java.util.List;

public interface ParticipateInterface {
    public List<Participate> getallParticipate();
    public Participate AddParticipate(Long projetId, Participate condidature);
    public Participate findParticipate(Long id);
    public void deleteParticipate(Long id);
}


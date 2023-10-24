package com.example.eventsservice.repo;

import com.example.eventsservice.entity.Participate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipateRepo extends JpaRepository<Participate,Long> {
}

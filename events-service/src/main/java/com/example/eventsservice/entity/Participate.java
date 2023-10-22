package com.example.eventsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table( name = "Participate")
@NoArgsConstructor

public class Participate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idParticipant")
    private Long idParticipant; // Clé primaire
    private String nom;
    private String prenom;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "EventId")
    @JsonIgnore

    private Event event;
}

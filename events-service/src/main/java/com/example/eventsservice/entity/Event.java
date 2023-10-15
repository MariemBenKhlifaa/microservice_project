package com.example.eventsservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table( name = "Event")
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EventId")
    private Long EventId; // Clé primaire
    private String titre ;
    private String description;
    private String Lieu;
    private String prix;
    private Date dateEvent;

}

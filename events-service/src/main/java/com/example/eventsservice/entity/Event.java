package com.example.eventsservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String image;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participate> participations = new ArrayList<>();


}

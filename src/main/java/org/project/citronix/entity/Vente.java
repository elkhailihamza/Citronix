package org.project.citronix.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    private double prix_unitaire;

    @ManyToOne
    private Recolte recolte;

    @ManyToOne
    private Client client;
}

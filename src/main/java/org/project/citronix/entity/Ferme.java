package org.project.citronix.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Ferme")
public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String superficie;
    private String localisation;
    private LocalDateTime date_de_creation;

    @OneToMany(mappedBy = "ferme")
    private List<Champ> champs;
}

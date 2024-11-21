package org.project.citronix.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date_de_plantation;
    private int age;

    @ManyToOne
    private Champ champ;

    @OneToMany(mappedBy = "arbre")
    private List<Recolte> recoltes;
}

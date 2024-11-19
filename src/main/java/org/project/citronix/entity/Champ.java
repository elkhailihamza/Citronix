package org.project.citronix.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String superficie;

    @ManyToOne
    private Ferme ferme;

    @OneToMany(mappedBy = "champ")
    private List<Arbre> arbres;
}

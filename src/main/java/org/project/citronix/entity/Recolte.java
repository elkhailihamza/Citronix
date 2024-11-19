package org.project.citronix.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime recolte_date;
    private int quantite;

    @ManyToOne
    private Arbre arbre;

    @OneToMany(mappedBy = "recolte")
    private List<Vente> ventes;
}

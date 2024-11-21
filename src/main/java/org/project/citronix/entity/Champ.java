package org.project.citronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double superficie;

    @ManyToOne
    private Ferme ferme;

    @OneToMany(mappedBy = "champ")
    private List<Arbre> arbres;
}

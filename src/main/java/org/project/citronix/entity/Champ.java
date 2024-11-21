package org.project.citronix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

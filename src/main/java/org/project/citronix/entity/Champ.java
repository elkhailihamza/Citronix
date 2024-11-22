package org.project.citronix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Arbre> arbres;
}

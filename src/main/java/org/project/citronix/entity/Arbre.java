package org.project.citronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date_de_plantation;
    private int age;

    @ManyToOne
    private Champ champ;

    @OneToMany(mappedBy = "arbre")
    private List<RecolteDetails> recolteDetails;
}

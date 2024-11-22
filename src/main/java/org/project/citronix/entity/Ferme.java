package org.project.citronix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private double superficie;
    private String localisation;
    private LocalDateTime date_de_creation;

    @PrePersist
    public void prePersist() {
        if (date_de_creation == null) {
            date_de_creation = LocalDateTime.now();
        }
    }

    @OneToMany(mappedBy = "ferme")
    private List<Champ> champs;
}

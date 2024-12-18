package org.project.citronix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private List<RecolteDetails> recolteDetails;
}

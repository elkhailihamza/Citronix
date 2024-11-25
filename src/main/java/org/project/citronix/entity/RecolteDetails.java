package org.project.citronix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecolteDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double quantite;

    @ManyToOne
    private Recolte recolte;

    @ManyToOne
    private Arbre arbre;
}

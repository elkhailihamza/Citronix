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
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime recolte_date;
    private int quantiteTotale;

    @OneToMany(mappedBy = "recolte")
    private List<RecolteDetails> recolteDetails;

    @OneToMany(mappedBy = "recolte")
    private List<Vente> ventes;
}

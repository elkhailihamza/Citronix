package org.project.citronix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.citronix.entity.type.Season;

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
    private double quantiteTotale;

    @OneToMany(mappedBy = "recolte")
    @JsonIgnore
    private List<RecolteDetails> recolteDetails;

    @Enumerated(EnumType.STRING)
    private Season season;

    @OneToMany(mappedBy = "recolte")
    @JsonIgnore
    private List<Vente> ventes;
}

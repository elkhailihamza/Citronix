package org.project.citronix.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.citronix.entity.RecolteDetails;
import org.project.citronix.entity.Vente;
import org.project.citronix.entity.type.Season;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecolteDTO {
    public interface Create extends Default {};

    private long id;

    private LocalDateTime recolte_date;
    private int quantiteTotale;

    private List<RecolteDetails> recolteDetails;

    @NotNull(message = "Season mustn't be null!", groups = {Create.class})
    private Season season;

    private List<Vente> ventes;
}

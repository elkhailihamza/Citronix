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
    public interface Update extends Default {};
    public interface Delete extends Default {};

    @NotNull(groups = Delete.class)
    private long id;

    private LocalDateTime recolte_date;

    @NotNull(message = "Change total quantity!", groups = Update.class)
    private int quantiteTotale;

    private List<RecolteDetails> recolteDetails;

    @NotNull(groups = Create.class)
    private List<Long> recolteDetailsIds;

    @NotNull(message = "Season mustn't be null!", groups = {Create.class})
    private Season season;

    private List<Vente> ventes;
}

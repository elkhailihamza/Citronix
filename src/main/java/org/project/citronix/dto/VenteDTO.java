package org.project.citronix.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.citronix.entity.Client;
import org.project.citronix.entity.Recolte;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteDTO {
    public interface Create extends Default {};
    public interface Update extends Default {};
    public interface Delete extends Default {};

    @NotNull(groups = Delete.class)
    private long id;
    private LocalDateTime date;

    @NotNull(groups = {Create.class, Update.class})
    private double prix_unitaire;

    @NotNull(groups = {Create.class, Update.class})
    private Recolte recolte;

    @NotNull(groups = {Create.class, Update.class})
    private Client client;
}

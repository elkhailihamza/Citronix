package org.project.citronix.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.citronix.entity.Champ;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FermeDTO {
    public interface Create extends Default {};
    public interface Update extends Default {};
    public interface Delete extends Default {};

    @NotNull(message = "Id needs to not be null!", groups = {Update.class, Delete.class})
    @NotBlank(message = "Id needs to not be blank!", groups = {Update.class, Delete.class})
    private long id;

    @NotNull(message = "Nom needs to not be null!", groups = {Create.class, Update.class})
    @NotBlank(message = "Nom needs to not be blank!", groups = {Create.class, Update.class})
    @Size(min = 3, message = "Nom needs to be at least 3 characters long!", groups = {Create.class, Update.class})
    private String nom;

    @DecimalMin(value = "0.1", message = "Superficie must be greater than or equal to 0.1", groups = {ChampDTO.Create.class, ChampDTO.Update.class})
    @DecimalMax(value = "100000.0", message = "Superficie must be less than or equal to 100,000", groups = {ChampDTO.Create.class, ChampDTO.Update.class})
    private double superficie;

    @NotNull(message = "Localisation needs to not be null!", groups = {Create.class, Update.class})
    @NotBlank(message = "Localisation needs to not be null!", groups = {Create.class, Update.class})
    private String localisation;

    private LocalDateTime date_de_creation;
    private List<Champ> champs;
}

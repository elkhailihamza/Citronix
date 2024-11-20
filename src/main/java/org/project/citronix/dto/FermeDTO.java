package org.project.citronix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(groups = Delete.class)
    @NotBlank(groups = Delete.class)
    private long id;

    @NotNull(message = "Nom needs to not be null!", groups = {Create.class, Update.class})
    @NotBlank(message = "Nom needs to not be blank!", groups = {Create.class, Update.class})
    @Size(min = 3, message = "Nom needs to be at least 3 characters long!", groups = {Create.class, Update.class})
    private String nom;

    @NotNull(message = "Superficie needs to not be null!", groups = {Create.class, Update.class})
    @NotBlank(message = "Superficie needs to not be null!", groups = {Create.class, Update.class})
    private String superficie;

    @NotNull(message = "Localisation needs to not be null!", groups = {Create.class, Update.class})
    @NotBlank(message = "Localisation needs to not be null!", groups = {Create.class, Update.class})
    private String localisation;

    private LocalDateTime date_de_creation;
    private List<Champ> champs;
}

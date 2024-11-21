package org.project.citronix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.citronix.entity.Arbre;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChampDTO {
    public interface Create extends Default {};
    public interface Update extends Default {};
    public interface Delete extends Default {};
    public interface Associate extends Default {};

    @NotNull(message = "Id needs to not be null", groups = {Delete.class, Update.class, Associate.class})
    private long id;

    @NotNull(message = "Superficie needs to not be null", groups = {Create.class, Update.class})
    private String superficie;

    @NotNull(message = "Ferme id needs to not be null", groups = {Update.class, Associate.class})
    private long fermeId;
    private List<Arbre> arbres;
}

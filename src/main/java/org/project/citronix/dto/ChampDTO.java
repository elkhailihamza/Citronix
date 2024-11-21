package org.project.citronix.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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

    @DecimalMin(value = "0.1", message = "Superficie must be greater than or equal to 0.1", groups = {Create.class, Update.class})
    @DecimalMax(value = "100000.0", message = "Superficie must be less than or equal to 100,000", groups = {Create.class, Update.class})
    private double superficie;

    @NotNull(message = "Ferme id needs to not be null", groups = {Update.class, Associate.class})
    private long fermeId;
    private List<Arbre> arbres;
}

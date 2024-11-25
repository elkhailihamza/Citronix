package org.project.citronix.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.citronix.entity.Arbre;
import org.project.citronix.entity.Recolte;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecolteDetailsDTO {
    public interface Create extends Default {};
    public interface Update extends Default {};
    public interface Delete extends Default {};
    public interface Log extends Default {};

    @NotNull(groups = RecolteDTO.Delete.class)
    private long id;

    @NotNull(groups = {Update.class, Log.class})
    private double quantite;

    @NotNull(groups = {Update.class, Log.class})
    private Recolte recolte;

    @NotNull(groups = {Create.class, Update.class, Log.class})
    private Arbre arbre;
}

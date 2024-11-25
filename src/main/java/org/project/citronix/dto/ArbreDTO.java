package org.project.citronix.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.citronix.entity.Champ;
import org.project.citronix.entity.RecolteDetails;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArbreDTO {
    public interface Create extends Default {};
    public interface Update extends Default {};
    public interface Delete extends Default {};
    public interface Association extends Default {};
    public interface Quantity extends Default {};

    @NotNull(groups = {Delete.class, Quantity.class})
    private long id;

    @NotNull(message = "Date de plantation mustn't be null!", groups = {Create.class, Update.class})
    @Past(message = "Date mustn't be in the future!", groups = {Create.class, Update.class})
    private LocalDateTime date_de_plantation;

    private int age;

    private Champ champ;

    @NotNull(message = "Champ id mustn't be null!", groups = Association.class)
    private long champId;

    private int annualProduction;

    private List<RecolteDetails> recolteDetails;
}

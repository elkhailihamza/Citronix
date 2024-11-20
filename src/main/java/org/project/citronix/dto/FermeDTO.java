package org.project.citronix.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FermeDTO {
    public interface create{};
    public interface update{};

    private long id;

    private String nom;
    private String superficie;
    private String localisation;
    private LocalDateTime date_de_creation;
}

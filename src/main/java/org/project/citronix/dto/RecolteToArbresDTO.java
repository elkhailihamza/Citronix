package org.project.citronix.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecolteToArbresDTO {
    private long recolteId;
    @NotNull(message = "arbres list needs to be not null!")
    private List<Long> arbreList;
}

package org.project.citronix.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArbreProductionDTO {
    @NotNull(message = "Id mustn't be null!")
    private long id;
    private int age;
    private double annualProduction;
    private double productionBySeason;
}

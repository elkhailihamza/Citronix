package org.project.citronix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FermeCriteriaDTO {
    private String nom;
    private String locatlisation;
    private double superficieMin;
    private double superficieMax;
}

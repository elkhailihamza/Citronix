package org.project.citronix.dto.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.citronix.dto.FermeDTO;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class FermeVM {
    private long id;
    private String nom;
    private String superficie;
    private String localisation;
    private String date_de_creation;

    public FermeVM(FermeDTO fermeDTO) {
        this.id = fermeDTO.getId();
        this.nom = fermeDTO.getNom();
        this.superficie = fermeDTO.getSuperficie();
        this.localisation = fermeDTO.getSuperficie();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date_de_creation = fermeDTO.getDate_de_creation().format(formatter);
    }
}

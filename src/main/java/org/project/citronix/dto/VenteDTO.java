package org.project.citronix.dto;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.citronix.entity.Client;
import org.project.citronix.entity.Recolte;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteDTO {
    public interface Create extends Default {};

    private long id;
    private LocalDateTime date;
    private double prix_unitaire;

    private Recolte recolte;
    private Client client;
}

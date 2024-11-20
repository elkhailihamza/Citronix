package org.project.citronix.controller;

import org.project.citronix.dto.FermeDTO;
import org.project.citronix.entity.Ferme;
import org.project.citronix.service.implementation.FermeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ferme")
public class FermeController {
    private final FermeService fermeService;

    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFerme(@RequestBody FermeDTO fermeDTO) {
        Ferme ferme = fermeService.toFerme(fermeDTO);
        return fermeService.createNewFerme(ferme);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateFerme(@PathVariable long id, @RequestBody FermeDTO fermeDTO) {
        Ferme ferme = fermeService.toFerme(fermeDTO);
        return fermeService.updateFerme(ferme, id);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFerme(@RequestBody long id) {
        return fermeService.deleteFerme(id);
    }
}

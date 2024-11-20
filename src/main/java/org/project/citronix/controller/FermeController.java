package org.project.citronix.controller;

import org.project.citronix.dto.FermeDTO;
import org.project.citronix.dto.vm.FermeVM;
import org.project.citronix.entity.Ferme;
import org.project.citronix.service.implementation.FermeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ferme")
@Validated
public class FermeController {
    private final FermeService fermeService;

    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFerme(@RequestBody @Validated(FermeDTO.Create.class) FermeDTO fermeDTO) {
        Ferme ferme = fermeService.toFerme(fermeDTO);
        return fermeService.createNewFerme(ferme);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateFerme(@PathVariable long id, @RequestBody @Validated(FermeDTO.Update.class) FermeDTO fermeDTO) {
        Ferme ferme = fermeService.toFerme(fermeDTO);
        return fermeService.updateFerme(ferme, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFerme(@PathVariable long id) {
        return fermeService.deleteFerme(id);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<FermeVM> viewFermeDetails(@PathVariable long id) {
        Ferme ferme = fermeService.fermeDetailsById(id);
        FermeDTO fermeDTO = fermeService.toFermeDTO(ferme);
        FermeVM fermeVM = new FermeVM(fermeDTO);
        return ResponseEntity.ok(fermeVM);
    }
}

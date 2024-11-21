package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.FermeDTO;
import org.project.citronix.entity.Ferme;
import org.project.citronix.service.implementation.FermeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ferme")
@RequiredArgsConstructor
@Validated
public class FermeController {
    private final FermeService fermeService;

    @PostMapping("/create")
    public ResponseEntity<?> createFerme(@RequestBody @Validated(FermeDTO.Create.class) FermeDTO fermeDTO) {
        Ferme ferme = fermeService.toFerme(fermeDTO);
        return fermeService.createNewFerme(ferme);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFerme(@RequestBody @Validated(FermeDTO.Update.class) FermeDTO fermeDTO) {
        return ResponseEntity.ok(fermeService.updateFerme(fermeDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFerme(@RequestBody @Validated(FermeDTO.Delete.class) FermeDTO fermeDTO) {
        fermeService.deleteFerme(fermeDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<FermeDTO> viewFermeDetails(@PathVariable long id) {
        FermeDTO fermeDTO = fermeService.fermeDetailsById(id);
        return ResponseEntity.ok(fermeDTO);
    }
}

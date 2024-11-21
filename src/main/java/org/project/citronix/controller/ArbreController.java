package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.ArbreDTO;
import org.project.citronix.service.implementation.ArbreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/arbre")
@RequiredArgsConstructor
@Validated
public class ArbreController {
    private final ArbreService arbreService;

    @PostMapping("/create")
    public ResponseEntity<?> createArbre(@RequestBody @Validated(ArbreDTO.Create.class) ArbreDTO arbreDTO) {
        return ResponseEntity.ok(arbreService.createNewArbre(arbreDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<ArbreDTO> updateArbre(@RequestBody @Validated(ArbreDTO.Update.class) ArbreDTO arbreDTO) {
        ArbreDTO arbreDTOUpdated = arbreService.updateArbre(arbreDTO);
        return ResponseEntity.ok(arbreDTOUpdated);
    }

    @GetMapping("/{id}/view")
    public ResponseEntity<?> viewArbre(@PathVariable long id) {
        ArbreDTO arbreDTO = arbreService.arbreDetailsById(id);
        return ResponseEntity.ok(arbreDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteArbre(@RequestBody @Validated(ArbreDTO.Association.class) ArbreDTO arbreDTO) {
        arbreService.deleteArbre(arbreDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @PostMapping("/associate/{id}")
    public ResponseEntity<ArbreDTO> associateArbreToChamp(@RequestBody @Validated(ArbreDTO.Association.class) ArbreDTO arbreDTO) {
        return ResponseEntity.ok(arbreService.associateToChamp(champDTO));
    }
}

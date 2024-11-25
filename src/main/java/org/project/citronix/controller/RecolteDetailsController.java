package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.RecolteDetailsDTO;
import org.project.citronix.dto.RecolteToArbresDTO;
import org.project.citronix.service.implementation.RecolteDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recolteDetails")
@Validated
@RequiredArgsConstructor
public class RecolteDetailsController {
    private final RecolteDetailsService recolteDetailsService;

    @PostMapping("/create")
    public RecolteDetailsDTO createRecolteDetails(@RequestBody @Validated(RecolteDetailsDTO.Create.class) RecolteDetailsDTO recolteDetailsDTO) {
        return recolteDetailsService.createNewRecolteDetails(recolteDetailsDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<RecolteDetailsDTO> updateRecolteDetails(@RequestBody @Validated(RecolteDetailsDTO.Update.class) RecolteDetailsDTO recolteDetailsDTO) {
        return ResponseEntity.ok(recolteDetailsService.updateRecolteDetails(recolteDetailsDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRecolteDetails(@RequestBody @Validated(RecolteDetailsDTO.Delete.class) RecolteDetailsDTO recolteDetailsDTO) {
        recolteDetailsService.deleteRecolteDetails(recolteDetailsDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<RecolteDetailsDTO> viewRecolteDetails(@PathVariable long id) {
        RecolteDetailsDTO recolteDetailsDTO = recolteDetailsService.getRecolteDetailsById(id);
        return ResponseEntity.ok(recolteDetailsDTO);
    }

    @PostMapping("/arbre/log")
    public ResponseEntity<RecolteDetailsDTO> logHarvestForTree(
            @RequestBody @Validated(RecolteDetailsDTO.Log.class) RecolteDetailsDTO recolteDetailsDTO) {
        return ResponseEntity.ok(recolteDetailsService.logRecolteForArbre(recolteDetailsDTO));
    }

    @PostMapping("/{recolteId}/associate-trees")
    public ResponseEntity<List<RecolteDetailsDTO>> associateArbresWithRecolte(
            @PathVariable Long recolteId,
            @RequestBody @Validated RecolteToArbresDTO recolteToArbresDTO) {
        recolteToArbresDTO.setRecolteId(recolteId);

        return ResponseEntity.ok(recolteDetailsService.associateArbresWithRecolte(recolteToArbresDTO));
    }
}

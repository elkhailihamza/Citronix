package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.ArbreDTO;
import org.project.citronix.dto.RecolteDTO;
import org.project.citronix.dto.RecolteToArbresDTO;
import org.project.citronix.service.implementation.RecolteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recolte")
@RequiredArgsConstructor
@Validated
public class RecolteController {
    private final RecolteService recolteService;

    @PostMapping("/create")
    public RecolteDTO createRecolte(@RequestBody @Validated(RecolteDTO.Create.class) RecolteDTO recolteDTO) {
        return recolteService.createNewRecolte(recolteDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<RecolteDTO> updateRecolte(@RequestBody @Validated(RecolteDTO.Update.class) RecolteDTO recolteDTO) {
        return ResponseEntity.ok(recolteService.updateRecolte(recolteDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRecolte(@RequestBody @Validated(RecolteDTO.Delete.class) RecolteDTO recolteDTO) {
        recolteService.deleteRecolte(recolteDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<RecolteDTO> viewRecolteDetails(@PathVariable long id) {
        RecolteDTO recolteDTO = recolteService.recolteDetailsById(id);
        return ResponseEntity.ok(recolteDTO);
    }

}

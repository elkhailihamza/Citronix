package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.RecolteDTO;
import org.project.citronix.service.implementation.RecolteDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recolteDetails")
@Validated
@RequiredArgsConstructor
public class RecolteDetailsController {
    private final RecolteDetailsService recolteDetailsService;

    @PostMapping("/create")
    public RecolteDTO createRecolte(@RequestBody @Validated(RecolteDTO.Create.class) RecolteDTO recolteDTO) {
        return recolteDetailsService.createNewRecolteDetails(recolteDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<RecolteDTO> updateRecolte(@RequestBody @Validated(RecolteDTO.Update.class) RecolteDTO recolteDTO) {
        return ResponseEntity.ok(recolteDetailsService.updateRecolteDetails(recolteDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRecolte(@RequestBody @Validated(RecolteDTO.Delete.class) RecolteDTO recolteDTO) {
        recolteDetailsService.deleteRecolteDetails(recolteDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<RecolteDTO> viewRecolteDetails(@PathVariable long id) {
        RecolteDTO recolteDTO = recolteDetailsService.recolteDetailsById(id);
        return ResponseEntity.ok(recolteDTO);
    }
}

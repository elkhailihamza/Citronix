package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.RevenuTotalDTO;
import org.project.citronix.dto.VenteDTO;
import org.project.citronix.service.implementation.VenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vente/")
@Validated
@RequiredArgsConstructor
public class VenteController {
    private final VenteService venteService;

    @PostMapping("/create")
    public VenteDTO createVente(@RequestBody @Validated(VenteDTO.Create.class) VenteDTO venteDTO) {
        return venteService.createNewVente(venteDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<VenteDTO> updateVente(@RequestBody @Validated(VenteDTO.Update.class) VenteDTO venteDTO) {
        return ResponseEntity.ok(venteService.updateVente(venteDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVente(@RequestBody @Validated(VenteDTO.Delete.class) VenteDTO venteDTO) {
        venteService.deleteVente(venteDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<VenteDTO> viewVenteDetails(@PathVariable long id) {
        VenteDTO venteDTO = venteService.venteDetailsById(id);
        return ResponseEntity.ok(venteDTO);
    }

    @GetMapping("/{id}/calculate/total")
    public ResponseEntity<RevenuTotalDTO> calcRevenuTotal(@PathVariable long id) {
        return ResponseEntity.ok(venteService.calcRevenuTotal(id));
    }
}

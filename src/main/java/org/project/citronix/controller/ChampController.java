package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.ChampDTO;
import org.project.citronix.entity.Champ;
import org.project.citronix.service.implementation.ChampService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/champ")
@RequiredArgsConstructor
@Validated
public class ChampController {
    private final ChampService champService;

    @PostMapping("/create")
    public ResponseEntity<?> createChamp(@RequestBody @Validated(ChampDTO.Create.class) ChampDTO champDTO) {
        Champ champ = champService.toChamp(champDTO);
        return ResponseEntity.ok(champService.createNewChamp(champ));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateChamp(@PathVariable long id, @RequestBody @Validated(ChampDTO.Create.class) ChampDTO champDTO) {
        ChampDTO champDTOUpdated = champService.updateChamp(champDTO, id);
        return ResponseEntity.ok(champDTOUpdated);
    }

    @GetMapping("/{id}/view")
    public ResponseEntity<?> viewChamp(@PathVariable long id) {
        ChampDTO champDTO = champService.champDetailsById(id);
        return ResponseEntity.ok(champDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteChamp(@PathVariable long id) {
        champService.deleteChamp(id);
        return ResponseEntity.ok("Deleted successfully!");
    }
}

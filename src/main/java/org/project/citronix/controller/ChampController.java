package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.ChampDTO;
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
        return ResponseEntity.ok(champService.createNewChamp(champDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<ChampDTO> updateChamp(@RequestBody @Validated(ChampDTO.Update.class) ChampDTO champDTO) {
        ChampDTO champDTOUpdated = champService.updateChamp(champDTO);
        return ResponseEntity.ok(champDTOUpdated);
    }

    @GetMapping("/{id}/view")
    public ResponseEntity<?> viewChamp(@PathVariable long id) {
        ChampDTO champDTO = champService.champDetailsById(id);
        return ResponseEntity.ok(champDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteChamp(@RequestBody @Validated(ChampDTO.Delete.class) ChampDTO champDTO) {
        champService.deleteChamp(champDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }

    @PostMapping("/associate/{id}")
    public ResponseEntity<ChampDTO> associateChampToFerme(@RequestBody @Validated(ChampDTO.Associate.class) ChampDTO champDTO) {
        return ResponseEntity.ok(champService.associateToFerme(champDTO));
    }

}

package org.project.citronix.controller;

import lombok.RequiredArgsConstructor;
import org.project.citronix.dto.RecolteDTO;
import org.project.citronix.service.implementation.RecolteService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

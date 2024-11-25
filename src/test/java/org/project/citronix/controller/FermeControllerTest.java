package org.project.citronix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.citronix.dto.FermeCriteriaDTO;
import org.project.citronix.dto.FermeDTO;
import org.project.citronix.entity.Ferme;
import org.project.citronix.service.implementation.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FermeController.class)
public class FermeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FermeService fermeService;

    @Autowired
    private ObjectMapper objectMapper;

    private FermeDTO fermeDTO;
    private FermeCriteriaDTO fermeCriteriaDTO;

    @BeforeEach
    public void setUp() {
        fermeDTO = new FermeDTO(1L, "Ferme A", 10.5, "Location A", null, null);
        fermeCriteriaDTO = new FermeCriteriaDTO("Ferme A", "Location A", 5.0, 20.0);
    }

    @Test
    public void createFerme_shouldReturnCreatedFerme() throws Exception {
        Ferme ferme = new Ferme(1L, "Ferme A", 10.5, "Location A", null, null);
        FermeDTO expectedFermeDTO = new FermeDTO(1L, "Ferme A", 10.5, "Location A", null, null);

        when(fermeService.toFerme(any(FermeDTO.class))).thenReturn(ferme);
        when(fermeService.createNewFerme(any(Ferme.class))).thenReturn(expectedFermeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/ferme/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fermeDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Ferme A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.superficie").value(10.5));
    }

    @Test
    public void updateFerme_shouldReturnUpdatedFerme() throws Exception {
        FermeDTO updatedFermeDTO = new FermeDTO(1L, "Updated Ferme", 12.5, "New Location", null, null);

        when(fermeService.updateFerme(any(FermeDTO.class))).thenReturn(updatedFermeDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/ferme/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fermeDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Updated Ferme"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.superficie").value(12.5));
    }

    @Test
    public void deleteFerme_shouldReturnSuccessMessage() throws Exception {
        doNothing().when(fermeService).deleteFerme(any(FermeDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/ferme/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fermeDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted successfully!"));
    }

    @Test
    public void viewFermeDetails_shouldReturnFermeDetails() throws Exception {
        FermeDTO expectedFermeDTO = new FermeDTO(1L, "Ferme A", 10.5, "Location A", null, null);

        when(fermeService.fermeDetailsById(anyLong())).thenReturn(expectedFermeDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/ferme/{id}/details", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Ferme A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.superficie").value(10.5));
    }

    @Test
    public void searchFermes_shouldReturnMatchingFermes() throws Exception {
        FermeDTO fermeDTO1 = new FermeDTO(1L, "Ferme A", 10.5, "Location A", null, null);
        FermeDTO fermeDTO2 = new FermeDTO(2L, "Ferme B", 20.0, "Location B", null, null);

        when(fermeService.searchFermes(any(FermeCriteriaDTO.class))).thenReturn(List.of(fermeDTO1, fermeDTO2));

        mockMvc.perform(MockMvcRequestBuilders.post("/ferme/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fermeCriteriaDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Ferme A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Ferme B"));
    }
}

package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.RevenuTotalDTO;
import org.project.citronix.dto.VenteDTO;
import org.project.citronix.dto.mapper.VenteMapper;
import org.project.citronix.entity.Vente;
import org.project.citronix.repository.VenteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VenteService extends GenericServiceImpl<Vente, Long> {
    private final VenteRepository repository;
    private final VenteMapper venteMapper;

    public VenteService(VenteRepository repository, VenteMapper venteMapper) {
        super(repository);
        this.repository = repository;
        this.venteMapper = venteMapper;
    }

    public Vente toVente(VenteDTO venteDTO) {
        return venteMapper.toVente(venteDTO);
    }

    public VenteDTO toVenteDTO(Vente vente) {
        return venteMapper.toVenteDTO(vente);
    }

    @Transactional
    public VenteDTO createNewVente(VenteDTO venteDTO) {
        Vente vente = toVente(venteDTO);
        vente.setDate(LocalDateTime.now());
        return toVenteDTO(save(vente));
    }

    @Transactional
    public VenteDTO updateVente(VenteDTO venteDTO) {
        Vente vente = toVente(venteDTO);
        Optional<Vente> oldVente = findById(vente.getId());
        if (oldVente.isPresent()) {
            vente.setId(vente.getId());
            vente.setDate(oldVente.get().getDate());
            save(vente);
            return toVenteDTO(vente);
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public void deleteVente(VenteDTO venteDTO) {
        if (findById(venteDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        deleteById(venteDTO.getId());
    }

    public VenteDTO venteDetailsById(long id) {
        Optional<Vente> vente = findById(id);
        return vente.map(this::toVenteDTO).orElseThrow(EntityNotFoundException::new);
    }

    public RevenuTotalDTO calcRevenuTotal(long id) {
        Optional<Vente> venteOpt = findById(id);
        if (venteOpt.isPresent()) {
            Vente vente = venteOpt.get();
            return new RevenuTotalDTO(vente.getId(), vente.getPrix_unitaire() * vente.getRecolte().getQuantiteTotale());
        }
        throw new EntityNotFoundException();
    }
}

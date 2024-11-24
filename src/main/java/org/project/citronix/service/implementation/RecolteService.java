package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.RecolteDTO;
import org.project.citronix.dto.mapper.RecolteMapper;
import org.project.citronix.entity.Recolte;
import org.project.citronix.repository.RecolteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecolteService extends GenericServiceImpl<Recolte, Long> {
    private final RecolteRepository repository;
    private final RecolteMapper recolteMapper;

    public RecolteService(RecolteRepository repository, RecolteMapper recolteMapper) {
        super(repository);
        this.repository = repository;
        this.recolteMapper = recolteMapper;
    }

    public Recolte toRecolte(RecolteDTO recolteDTO) {
        return recolteMapper.toRecolte(recolteDTO);
    }

    public RecolteDTO toRecolteDTO(Recolte recolte) {
        return recolteMapper.toRecolteDTO(recolte);
    }

    @Transactional
    public RecolteDTO createNewRecolte(RecolteDTO recolteDTO) {
        Recolte recolte = toRecolte(recolteDTO);
        return toRecolteDTO(save(recolte));
    }

    @Transactional
    public RecolteDTO updateRecolte(RecolteDTO recolteDTO) {
        Recolte recolte = toRecolte(recolteDTO);
        Optional<Recolte> oldRecolte = findById(recolte.getId());
        if (oldRecolte.isPresent()) {
            recolte.setId(recolte.getId());
            recolte.setRecolte_date(oldRecolte.get().getRecolte_date());
            save(recolte);
            return toRecolteDTO(recolte);
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public void deleteRecolte(RecolteDTO recolteDTO) {
        if (findById(recolteDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        deleteById(recolteDTO.getId());
    }

    public RecolteDTO recolteDetailsById(long id) {
        Optional<Recolte> recolte = findById(id);
        return recolte.map(this::toRecolteDTO).orElseThrow(EntityNotFoundException::new);
    }
}

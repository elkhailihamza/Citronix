package org.project.citronix.service.implementation;

import jakarta.transaction.Transactional;
import org.project.citronix.dto.RecolteDTO;
import org.project.citronix.dto.mapper.RecolteMapper;
import org.project.citronix.entity.Recolte;
import org.project.citronix.repository.RecolteRepository;
import org.springframework.stereotype.Service;

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
}

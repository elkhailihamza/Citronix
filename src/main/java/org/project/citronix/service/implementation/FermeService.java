package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.domain.specification.FermeSpecification;
import org.project.citronix.dto.FermeCriteriaDTO;
import org.project.citronix.dto.FermeDTO;
import org.project.citronix.dto.mapper.FermeMapper;
import org.project.citronix.entity.Ferme;
import org.project.citronix.repository.FermeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class FermeService extends GenericServiceImpl<Ferme, Long> {
    private final FermeRepository repository;
    private final FermeMapper fermeMapper;

    public FermeService(FermeRepository repository, FermeMapper fermeMapper) {
        super(repository);
        this.repository = repository;
        this.fermeMapper = fermeMapper;
    }

    public Ferme toFerme(FermeDTO fermeDTO) {
        return fermeMapper.toFerme(fermeDTO);
    }

    public FermeDTO toFermeDTO(Ferme ferme) {
        return fermeMapper.toFermeDTO(ferme);
    }

    @Transactional
    public FermeDTO createNewFerme(Ferme ferme) {
        return toFermeDTO(save(ferme));
    }

    @Transactional
    public FermeDTO updateFerme(FermeDTO fermeDTO) {
        Ferme ferme = toFerme(fermeDTO);
        Optional<Ferme> oldFerme = findById(ferme.getId());
        if (oldFerme.isPresent()) {
            ferme.setId(ferme.getId());
            ferme.setDate_de_creation(oldFerme.get().getDate_de_creation());
            save(ferme);
            return toFermeDTO(ferme);
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public void deleteFerme(FermeDTO fermeDTO) {
        if (findById(fermeDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        deleteById(fermeDTO.getId());
    }

    public FermeDTO fermeDetailsById(long id) {
        Optional<Ferme> ferme = findById(id);
        return ferme.map(this::toFermeDTO).orElseThrow(EntityNotFoundException::new);
    }

    public List<FermeDTO> searchFermes(@RequestBody FermeCriteriaDTO fermeCriteriaDTO) {
        Specification<Ferme> specification = FermeSpecification.searchFermes(fermeCriteriaDTO.getNom(), fermeCriteriaDTO.getLocatlisation(), fermeCriteriaDTO.getSuperficieMin(), fermeCriteriaDTO.getSuperficieMax());
        List<Ferme> fermeList = repository.findAll(specification);
        return fermeList.stream().map(this::toFermeDTO).toList();
    }
}

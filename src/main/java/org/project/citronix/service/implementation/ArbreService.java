package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.ArbreDTO;
import org.project.citronix.dto.ChampDTO;
import org.project.citronix.dto.mapper.ArbreMapper;
import org.project.citronix.entity.Arbre;
import org.project.citronix.entity.Champ;
import org.project.citronix.exception.SuperficieNonCompatible;
import org.project.citronix.repository.ArbreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArbreService extends GenericServiceImpl<Arbre, Long> {
    private final ArbreRepository repository;
    private final ArbreMapper arbreMapper;
    private final ChampService champService;

    public ArbreService(ArbreRepository repository, ArbreMapper arbreMapper, ChampService champService) {
        super(repository);
        this.repository = repository;
        this.arbreMapper = arbreMapper;
        this.champService = champService;
    }

    public Arbre toArbre(ArbreDTO arbreDTO) {
        return arbreMapper.toEntity(arbreDTO);
    }

    public ArbreDTO toArbreDTO(Arbre arbre) {
        return arbreMapper.toEntityDTO(arbre);
    }

    @Transactional
    public ArbreDTO createNewArbre(ArbreDTO arbreDTO) {
        Arbre arbre = save(toArbre(arbreDTO));
        return toArbreDTO(arbre);
    }

    @Transactional
    public ArbreDTO updateArbre(ArbreDTO arbreDTO) {
        Arbre arbre = toArbre(arbreDTO);
        Optional<Arbre> oldArbre = findById(arbre.getId());
        if (oldArbre.isPresent()) {
            arbre.setId(arbre.getId());
            arbre.setDate_de_plantation(oldArbre.get().getDate_de_plantation());
            save(arbre);
            return toArbreDTO(arbre);
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public void deleteArbre(ArbreDTO arbreDTO) {
        if (findById(arbreDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        deleteById(arbreDTO.getId());
    }

    public ArbreDTO arbreDetailsById(long id) {
        Optional<Arbre> arbre = findById(id);
        return arbre.map(this::toArbreDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public ArbreDTO associateToArbre(ArbreDTO arbreDTO) {
        Optional<Arbre> arbre = findById(arbreDTO.getId());
        Optional<Champ> champ = champService.findById(arbreDTO.getChampId());
        if (arbre.isPresent() && champ.isPresent()) {
            arbre.get().setChamp(champ.get());
            return updateArbre(toArbreDTO(arbre.get()));
        }
        throw new EntityNotFoundException();
    }


}

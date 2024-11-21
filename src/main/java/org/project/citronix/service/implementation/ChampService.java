package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.ChampDTO;
import org.project.citronix.dto.mapper.ChampMapper;
import org.project.citronix.entity.Champ;
import org.project.citronix.repository.ChampRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChampService extends GenericServiceImpl<Champ, Long> {
    private final ChampRepository repository;
    private final ChampMapper champMapper;

    public ChampService(ChampRepository repository, ChampMapper champMapper) {
        super(repository);
        this.repository = repository;
        this.champMapper = champMapper;
    }

    public Champ toChamp(ChampDTO champDTO) {
        return champMapper.toEntity(champDTO);
    }

    public ChampDTO toChampDTO(Champ champ) {
        return champMapper.toEntityDTO(champ);
    }

    @Transactional
    public ChampDTO createNewChamp(ChampDTO champDTO) {
        Champ champ = toChamp(champDTO);
        Champ savedChamp = save(champ);
        return toChampDTO(savedChamp);
    }

    public ChampDTO champDetailsById(long id) {
        Optional<Champ> champ = findById(id);
        return champ.map(this::toChampDTO).orElse(null); // throw exception
    }

    @Transactional
    public ChampDTO updateChamp(ChampDTO champDTO) {
        Champ champ = toChamp(champDTO);
        Optional<Champ> oldChamp = findById(champ.getId());
        if (oldChamp.isPresent()) {
            Champ updatedChamp = oldChamp.get();
            updatedChamp.setSuperficie(champ.getSuperficie());
            updatedChamp = save(updatedChamp);
            return toChampDTO(updatedChamp);
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public void deleteChamp(ChampDTO champDTO) {
        Optional<Champ> champ = findById(champDTO.getId());
        if (findById(champDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        deleteById(champDTO.getId());
    }
}

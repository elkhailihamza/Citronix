package org.project.citronix.service.implementation;

import jakarta.transaction.Transactional;
import org.project.citronix.dto.ChampDTO;
import org.project.citronix.dto.mapper.ChampMapper;
import org.project.citronix.entity.Champ;
import org.project.citronix.repository.ChampRepository;
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
    public ChampDTO updateChamp(ChampDTO champDTO, long id) {
        Champ champ = toChamp(champDTO);
        Optional<Champ> oldChamp = findById(id);
        if (oldChamp.isPresent()) {
            Champ updatedChamp = oldChamp.get();
            updatedChamp.setSuperficie(champ.getSuperficie());
            updatedChamp = save(updatedChamp);
            return toChampDTO(updatedChamp);
        }
        return null; // throw exception
    }

    @Transactional
    public Champ deleteChamp(long id) {
        Optional<Champ> oldChamp = findById(id);
        if (oldChamp.isPresent()) {
            deleteById(id);
        }
        return null; // throw exception
    }
}

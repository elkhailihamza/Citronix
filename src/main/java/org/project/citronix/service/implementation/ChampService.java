package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.ChampDTO;
import org.project.citronix.dto.FermeDTO;
import org.project.citronix.dto.mapper.ChampMapper;
import org.project.citronix.entity.Champ;
import org.project.citronix.entity.Ferme;
import org.project.citronix.exception.SuperficieNonCompatibleException;
import org.project.citronix.repository.ChampRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChampService extends GenericServiceImpl<Champ, Long> {
    private final ChampRepository repository;
    private final ChampMapper champMapper;
    private final FermeService fermeService;

    public ChampService(ChampRepository repository, ChampMapper champMapper, FermeService fermeService) {
        super(repository);
        this.repository = repository;
        this.champMapper = champMapper;
        this.fermeService = fermeService;
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
        if (findById(champDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        deleteById(champDTO.getId());
    }

    @Transactional
    public ChampDTO associateToFerme(ChampDTO champDTO) {
        Optional<Champ> champ = findById(champDTO.getId());
        Optional<Ferme> ferme = fermeService.findById(champDTO.getFermeId());
        if (champ.isPresent() && ferme.isPresent()) {
            FermeDTO fermeDTO = fermeService.toFermeDTO(ferme.get());
            checkIfSuperficieIsCompatible(fermeDTO, champDTO);
            champ.get().setFerme(ferme.get());
            return updateChamp(toChampDTO(champ.get()));
        }
        throw new EntityNotFoundException();
    }

    private void checkIfSuperficieIsCompatible(FermeDTO fermeDTO, ChampDTO champDTO) {
        boolean compatible = true;
        String reason = "";
        double halfSuperficie = fermeDTO.getSuperficie() * 50/100;
        if (champDTO.getSuperficie() > halfSuperficie) {
            compatible = false;
            reason = "Champ superficie surpasses 50% of ferme total superficie!";
        }

        double spaceTaken = repository.sumAllChampSuperficieByFermeId(fermeDTO.getId());
        if (fermeDTO.getSuperficie() < champDTO.getSuperficie() + spaceTaken) {
            compatible = false;
            reason = "The surface area of the Champ exceeds the available space in the Ferme.";
        }

        if (!compatible) {
            throw new SuperficieNonCompatibleException(reason);
        }
    }
}

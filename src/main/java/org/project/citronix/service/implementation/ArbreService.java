package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.ArbreDTO;
import org.project.citronix.dto.ArbreProductionDTO;
import org.project.citronix.dto.mapper.ArbreMapper;
import org.project.citronix.entity.Arbre;
import org.project.citronix.entity.Champ;
import org.project.citronix.exception.ArbreDateNotCompatible;
import org.project.citronix.exception.SuperficieNonCompatibleException;
import org.project.citronix.exception.ValueNotExpectedException;
import org.project.citronix.repository.ArbreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
        return arbreMapper.toArbre(arbreDTO);
    }

    public ArbreDTO toArbreDTO(Arbre arbre) {
        return arbreMapper.toArbreDTO(arbre);
    }

    @Transactional
    public ArbreDTO createNewArbre(ArbreDTO arbreDTO) {
        LocalDateTime arbreDate = arbreDTO.getDate_de_plantation();
        int currentMonth = arbreDate.getMonthValue();

        if (currentMonth < 3 || currentMonth > 5) {
            throw new ArbreDateNotCompatible("Planting is only allowed between March and May.");
        }

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
        return arbre.map(a -> {
            ArbreDTO arbreDTO = toArbreDTO(a);
            Period period = calcArbreAge(arbreDTO);
            arbreDTO.setAge(period.getYears());
            return arbreDTO;
        }).orElseThrow(EntityNotFoundException::new);
    }

    public Period calcArbreAge(ArbreDTO arbreDTO) {
        return Period.between(arbreDTO.getDate_de_plantation().toLocalDate(), LocalDate.now());
    }

    @Transactional
    public ArbreDTO associateToChamp(ArbreDTO arbreDTO, long champId) {
        Arbre arbre = findById(arbreDTO.getId()).orElseThrow(EntityNotFoundException::new);
        Champ champ = champService.findById(champId).orElseThrow(EntityNotFoundException::new);

        int arbreCount = champ.getArbres().size();
        double maxArbreCapacity = champ.getSuperficie() / 100;
        if (arbreCount >= maxArbreCapacity) {
            throw new SuperficieNonCompatibleException("Chaque champ doit contenir un nombre d'arbres tel que la densité maximale est de 100 arbres par hectare (10 arbres par 1 000 m²)");
        }

        arbre.setChamp(champ);
        return updateArbre(toArbreDTO(arbre));
    }

    public ArbreProductionDTO calcYearlyProduction(ArbreProductionDTO arbreProductionDTO) {
        Arbre arbre = findById(arbreProductionDTO.getId()).orElseThrow(EntityNotFoundException::new);

        ArbreDTO arbreDTO = toArbreDTO(arbre);
        int age = calcArbreAge(arbreDTO).getYears();
        arbreProductionDTO.setAge(age);

        double productionBySeason = seasonalProduction(arbreProductionDTO);
        arbreProductionDTO.setProductionBySeason(productionBySeason);
        arbreProductionDTO.setAnnualProduction(productionBySeason * 4);

        return arbreProductionDTO;
    }

    public double seasonalProduction(ArbreProductionDTO arbreProductionDTO) {
        int age = arbreProductionDTO.getAge();
        if (age < 3) {
            return 2.5;
        } else if (age > 3 && age < 10) {
            return 12;
        } else if (age > 10 && age < 20) {
            return 20;
        } else if (age > 20) {
            return 0; // non-productive
        }
        throw new ValueNotExpectedException("Age value not expected!");
    }
}

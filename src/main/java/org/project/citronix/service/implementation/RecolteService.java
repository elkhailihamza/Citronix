package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.RecolteDTO;
import org.project.citronix.dto.RecolteToArbresDTO;
import org.project.citronix.dto.mapper.RecolteMapper;
import org.project.citronix.entity.Arbre;
import org.project.citronix.entity.Champ;
import org.project.citronix.entity.Recolte;
import org.project.citronix.entity.RecolteDetails;
import org.project.citronix.repository.ArbreRepository;
import org.project.citronix.repository.RecolteDetailsRepository;
import org.project.citronix.repository.RecolteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecolteService extends GenericServiceImpl<Recolte, Long> {
    private final RecolteRepository repository;
    private final RecolteMapper recolteMapper;
    private final RecolteDetailsRepository recolteDetailsRepository;
    private final RecolteRepository recolteRepository;
    private final ArbreRepository arbreRepository;
    private final RecolteDetailsService recolteDetailsService;

    public RecolteService(RecolteRepository repository, RecolteMapper recolteMapper,
                          RecolteDetailsRepository recolteDetailsRepository, RecolteRepository recolteRepository, ArbreRepository arbreRepository, RecolteDetailsService recolteDetailsService) {
        super(repository);
        this.repository = repository;
        this.recolteMapper = recolteMapper;
        this.recolteDetailsRepository = recolteDetailsRepository;
        this.recolteRepository = recolteRepository;
        this.arbreRepository = arbreRepository;
        this.recolteDetailsService = recolteDetailsService;
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
        List<RecolteDetails> recolteDetails = recolteDTO.getRecolteDetailsIds().stream()
                .map(r -> recolteDetailsService.findById(r)
                        .orElseThrow(EntityNotFoundException::new))
                .peek(r -> r.setRecolte(recolte))
                .peek(r -> recolte.setQuantiteTotale(recolte.getQuantiteTotale() + r.getQuantite()))
                .toList();
        recolte.setRecolteDetails(recolteDetails);
        recolte.setRecolte_date(LocalDateTime.now());
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

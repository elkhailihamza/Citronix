package org.project.citronix.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.project.citronix.dto.RecolteDetailsDTO;
import org.project.citronix.dto.RecolteToArbresDTO;
import org.project.citronix.dto.mapper.RecolteDetailsMapper;
import org.project.citronix.entity.Arbre;
import org.project.citronix.entity.Recolte;
import org.project.citronix.entity.RecolteDetails;
import org.project.citronix.repository.ArbreRepository;
import org.project.citronix.repository.RecolteDetailsRepository;
import org.project.citronix.repository.RecolteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecolteDetailsService extends GenericServiceImpl<RecolteDetails, Long> {
    private final RecolteDetailsRepository repository;
    private final RecolteDetailsMapper recolteDetailsMapper;
    private final ArbreRepository arbreRepository;
    private final RecolteRepository recolteRepository;

    public RecolteDetailsService(RecolteDetailsRepository repository, RecolteDetailsMapper recolteDetailsMapper, ArbreRepository arbreRepository, RecolteRepository recolteRepository) {
        super(repository);
        this.repository = repository;
        this.recolteDetailsMapper = recolteDetailsMapper;
        this.arbreRepository = arbreRepository;
        this.recolteRepository = recolteRepository;
    }

    public RecolteDetails toRecolteDetails (RecolteDetailsDTO recolteDetailsDTO) {
        return recolteDetailsMapper.toRecolteDetails(recolteDetailsDTO);
    }

    public RecolteDetailsDTO toRecolteDetailsDTO(RecolteDetails recolteDetails) {
        return recolteDetailsMapper.toRecolteDetailsDTO(recolteDetails);
    }

    @Transactional
    public RecolteDetailsDTO createNewRecolteDetails(RecolteDetailsDTO recolteDetailsDTO) {
        RecolteDetails recolteDetails = toRecolteDetails(recolteDetailsDTO);
        return toRecolteDetailsDTO(save(recolteDetails));
    }

    @Transactional
    public RecolteDetailsDTO updateRecolteDetails(RecolteDetailsDTO recolteDetailsDTO) {
        RecolteDetails recolteDetails = toRecolteDetails(recolteDetailsDTO);
        Optional<RecolteDetails> oldRecolteDetails = findById(recolteDetails.getId());
        if (oldRecolteDetails.isPresent()) {
            recolteDetails.setId(recolteDetails.getId());
            save(recolteDetails);
            return toRecolteDetailsDTO(recolteDetails);
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public void deleteRecolteDetails(RecolteDetailsDTO recolteDetailsDTO) {
        if (findById(recolteDetailsDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException();
        }
        deleteById(recolteDetailsDTO.getId());
    }

    public RecolteDetailsDTO getRecolteDetailsById(long id) {
        Optional<RecolteDetails> recolteDetails = findById(id);
        return recolteDetails.map(this::toRecolteDetailsDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public RecolteDetailsDTO logRecolteForArbre(RecolteDetailsDTO recolteDetailsDTO) {
        Recolte recolte = recolteRepository.findById(recolteDetailsDTO.getRecolte().getId())
                .orElseThrow(EntityNotFoundException::new);

        Arbre arbre = arbreRepository.findById(recolteDetailsDTO.getArbre().getId())
                .orElseThrow(EntityNotFoundException::new);

        RecolteDetails recolteDetails = RecolteDetails.builder()
                .recolte(recolte)
                .arbre(arbre)
                .quantite(recolteDetailsDTO.getQuantite())
                .build();

        return toRecolteDetailsDTO(save(recolteDetails));
    }

    public List<RecolteDetailsDTO> associateArbresWithRecolte(RecolteToArbresDTO recolteToArbresDTO) {
        Recolte recolte = recolteRepository.findById(recolteToArbresDTO.getRecolteId())
                .orElseThrow(EntityNotFoundException::new);

        List<Arbre> arbres = arbreRepository.findAllById(recolteToArbresDTO.getArbreList());

        List<RecolteDetails> recolteDetailsList = arbres.stream()
                .map(arbre -> RecolteDetails.builder()
                        .arbre(arbre)
                        .recolte(recolte)
                        .build())
                .toList();

        recolte.setRecolteDetails(recolteDetailsList);

        return repository.saveAll(recolteDetailsList).stream().map(this::toRecolteDetailsDTO).toList();
    }
}

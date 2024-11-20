package org.project.citronix.service.implementation;

import jakarta.transaction.Transactional;
import org.project.citronix.dto.FermeDTO;
import org.project.citronix.dto.mapper.FermeMapper;
import org.project.citronix.entity.Ferme;
import org.project.citronix.repository.FermeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return fermeMapper.toEntity(fermeDTO);
    }

    @Transactional
    public ResponseEntity<?> createNewFerme(Ferme ferme) {
        save(ferme);
        return ResponseEntity.ok("Ferme Cree!");
    }

    @Transactional
    public ResponseEntity<?> updateFerme(Ferme ferme, long id) {
        Optional<Ferme> oldFerme = findById(id);
        if (oldFerme.isPresent()) {
            save(ferme);
            return ResponseEntity.ok("Ferme updated!");
        }
        return null; // throw EntityNotFound exception
    }

    @Transactional
    public ResponseEntity<?> deleteFerme(long id) {
        Optional<Ferme> oldFerme = findById(id);
        if (oldFerme.isPresent()) {
            deleteById(id);
            return ResponseEntity.ok("Ferme deleted!");
        }
        return null; // throw EntityNotFound exception
    }
}

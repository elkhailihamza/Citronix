package org.project.citronix.repository;

import org.project.citronix.entity.RecolteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecolteDetailsRepository extends JpaRepository<RecolteDetails, Long> {
    @Query("SELECT SUM(rd.quantite) FROM RecolteDetails rd WHERE rd.arbre.id = :arbreId")
    Double sumQuantityByArbreId(Long arbreId);
}

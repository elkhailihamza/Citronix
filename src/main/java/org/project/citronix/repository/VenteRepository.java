package org.project.citronix.repository;

import org.project.citronix.entity.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Long> {
}

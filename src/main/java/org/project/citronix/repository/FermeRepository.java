package org.project.citronix.repository;

import org.project.citronix.entity.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FermeRepository extends JpaRepository<Ferme, Long>, JpaSpecificationExecutor<Ferme> {

}

package org.project.citronix.repository;

import org.project.citronix.entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChampRepository extends JpaRepository<Champ, Long> {
    @Query("SELECT SUM(c.superficie) FROM Champ c WHERE c.ferme.id = :fermeId")
    double sumAllChampSuperficieByFermeId(@Param("fermeId") long fermeId);
}

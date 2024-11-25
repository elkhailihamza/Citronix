package org.project.citronix.domain.specification;

import jakarta.persistence.criteria.Predicate;
import org.project.citronix.entity.Ferme;
import org.springframework.data.jpa.domain.Specification;

public class FermeSpecification {
    public static Specification<Ferme> searchFermes(String nom, String localisation, Double superficieMin, Double superficieMax) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (nom != null && !nom.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("nom")), "%" + nom.toLowerCase() + "%"));
            }

            if (localisation != null && !localisation.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("localisation")), "%" + localisation.toLowerCase() + "%"));
            }

            if (superficieMin != null && superficieMin > 0) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("superficie"), superficieMin));
            }

            if (superficieMax != null && superficieMax > 0) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("superficie"), superficieMax));
            }

            return predicate;
        };
    }
}

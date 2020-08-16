package com.rstepanchuk.miniplantpotstock.repository;

import com.rstepanchuk.miniplantpotstock.entity.catalog.PotSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotSetRepository extends JpaRepository<PotSet, Long> {
}

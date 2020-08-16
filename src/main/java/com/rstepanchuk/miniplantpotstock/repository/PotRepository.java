package com.rstepanchuk.miniplantpotstock.repository;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotRepository extends JpaRepository<Pot, Long> {
}

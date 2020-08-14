package com.rstepanchuk.miniplantpotstock.repository;

import com.rstepanchuk.miniplantpotstock.entity.production.ProductionSupply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionSupplyRepository extends JpaRepository<ProductionSupply, Long> {
}

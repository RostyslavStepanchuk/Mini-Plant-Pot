package com.rstepanchuk.miniplantpotstock.repository;

import com.rstepanchuk.miniplantpotstock.entity.production.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
}

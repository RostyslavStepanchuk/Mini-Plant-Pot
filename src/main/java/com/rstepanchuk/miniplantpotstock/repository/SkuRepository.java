package com.rstepanchuk.miniplantpotstock.repository;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<Sku, String> {
}

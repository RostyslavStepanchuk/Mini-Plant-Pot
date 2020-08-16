package com.rstepanchuk.miniplantpotstock.unit.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.production.ProductionSupply;
import com.rstepanchuk.miniplantpotstock.repository.ProductionSupplyRepository;
import com.rstepanchuk.miniplantpotstock.service.ProductionSupplyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductionSupplyServiceTest {

  @InjectMocks
  private ProductionSupplyService productionSupplyService;

  @Mock
  private ProductionSupplyRepository repository;

  @Test
  void getProductionSuppliesShouldReturnListOfProductionSupplies() {
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();

    ProductionSupply productionSupply = ProductionSupply.builder().id(480L)
        .pot(superPuperPot)
        .supplyDate(Calendar.getInstance().getTime())
        .suppliedQuantity(4)
        .wasOrdered(true)
        .build();

    ArrayList<ProductionSupply> productionSupplies = new ArrayList<ProductionSupply>() {{
      add(productionSupply);
    }};

    when(repository.findAll()).thenReturn(productionSupplies);

    List<ProductionSupply> result = productionSupplyService.getProductionSupplies();

    assertIterableEquals(productionSupplies, result);
  }
}
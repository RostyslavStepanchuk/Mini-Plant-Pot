package com.rstepanchuk.miniplantpotstock.unit.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.production.ProductionOrder;
import com.rstepanchuk.miniplantpotstock.repository.ProductionOrderRepository;
import com.rstepanchuk.miniplantpotstock.service.ProductionOrderService;
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
class ProductionOrderServiceTest {

  @InjectMocks
  private ProductionOrderService productionOrderService;

  @Mock
  private ProductionOrderRepository repository;

  @Test
  void getProductionOrdersShouldReturnListOfProductionOrders() {
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();
    ProductionOrder prodOrder = ProductionOrder.builder()
        .id(327L)
        .pot(superPuperPot)
        .submitDate(Calendar.getInstance().getTime())
        .initialDeadline(Calendar.getInstance().getTime())
        .deadlineChanges(1)
        .orderedQuantity(4)
        .fulfilledQuantity(4)
        .delivered(Calendar.getInstance().getTime())
        .build();

    ArrayList<ProductionOrder> productionOrders = new ArrayList<ProductionOrder>() {{
      add(prodOrder);
    }};

    when(repository.findAll()).thenReturn(productionOrders);

    List<ProductionOrder> result = productionOrderService.getProductionOrders();

    assertIterableEquals(result, productionOrders);

  }
}
package com.rstepanchuk.miniplantpotstock.unit.controller;

import com.rstepanchuk.miniplantpotstock.controller.ProductionOrderController;
import com.rstepanchuk.miniplantpotstock.dto.production.order.ProductionOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionOrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductionOrderControllerTest {

  @InjectMocks
  private ProductionOrderController productionOrderController;

  @Mock
  private ProductionOrderMapper productionOrderMapper;

  @Test
  void getProductionOrdersShouldReturnResponseWithListOfOrders() {
    List<ProductionOrderDtoOut> orders = new ArrayList<>();

    when(productionOrderMapper.getProductionOrders()).thenReturn(orders);

    ResponseEntity<List<ProductionOrderDtoOut>> response = productionOrderController.getProductionOrders();

    assertEquals(response.getStatusCodeValue(), 200);
    assertEquals(response.getBody().size(), orders.size());
  }
}

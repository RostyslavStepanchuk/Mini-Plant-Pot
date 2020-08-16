package com.rstepanchuk.miniplantpotstock.unit.controller;

import com.rstepanchuk.miniplantpotstock.controller.ProductionSupplyController;
import com.rstepanchuk.miniplantpotstock.dto.production.supply.ProductionSupplyDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionSupplyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductionSupplyControllerTest {

  @InjectMocks
  ProductionSupplyController productionSupplyController;

  @Mock
  ProductionSupplyMapper productionSupplyMapper;

  @Test
  void getProductionSuppliesShouldReturnResponseWithListOfProductionSupplies(){
    List<ProductionSupplyDtoOut> supplies = new ArrayList<>();

    when(productionSupplyMapper.getProductionSupplies()).thenReturn(supplies);

    ResponseEntity<List<ProductionSupplyDtoOut>> productionSupplies = productionSupplyController.getProductionSupplies();

    assertEquals(productionSupplies.getStatusCodeValue(), 200);
    assertEquals(Objects.requireNonNull(productionSupplies.getBody()).size(), supplies.size());
  }

}

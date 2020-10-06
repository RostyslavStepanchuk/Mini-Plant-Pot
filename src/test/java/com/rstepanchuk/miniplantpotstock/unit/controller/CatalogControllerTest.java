package com.rstepanchuk.miniplantpotstock.unit.controller;

import com.rstepanchuk.miniplantpotstock.controller.CatalogController;
import com.rstepanchuk.miniplantpotstock.dto.catalog.PotDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.SkuMapper;
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
class CatalogControllerTest {

  @InjectMocks
  CatalogController catalogController;

  @Mock
  SkuMapper skuMapper;

  @Test
  void getAllPotsShouldReturnResponseWithListOfPots () {
    List<PotDtoOut> pots = new ArrayList<>();

    when(skuMapper.getPots()).thenReturn(pots);

    ResponseEntity<List<PotDtoOut>> response = catalogController.getPots();

    assertEquals(response.getStatusCodeValue(), 200);
    assertEquals(response.getBody().size(), pots.size());
  }
}

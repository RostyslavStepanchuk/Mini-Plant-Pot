package com.rstepanchuk.miniplantpotstock.unit.controller;

import com.rstepanchuk.miniplantpotstock.controller.PotController;
import com.rstepanchuk.miniplantpotstock.dto.pot.PotDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.pot.SkuDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.mapper.PotMapper;
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
public class PotControllerTest {

  @InjectMocks
  PotController potController;

  @Mock
  PotMapper potMapper;

  @Test
  public void getAllPotsShouldReturnResponseWithListOfPots () {
    List<PotDtoOut> pots = new ArrayList<>();

    when(potMapper.getAllPots()).thenReturn(pots);

    ResponseEntity<List<PotDtoOut>> response = potController.getAllPots();

    assertEquals(response.getStatusCodeValue(), 200);
    assertEquals(response.getBody().size(), pots.size());
  }


  @Test
  public void getAllSkuShouldReturnResponseWithListOfSku () {
    List<SkuDtoOut> skus = new ArrayList<>();

    when(potMapper.getAllSku()).thenReturn(skus);

    ResponseEntity<List<SkuDtoOut>> response = potController.getAllSku();

    assertEquals(response.getStatusCodeValue(), 200);
    assertEquals(response.getBody().size(), skus.size());
  }



}

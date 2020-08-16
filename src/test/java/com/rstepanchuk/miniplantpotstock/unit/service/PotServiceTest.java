package com.rstepanchuk.miniplantpotstock.unit.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.repository.PotRepository;
import com.rstepanchuk.miniplantpotstock.service.PotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PotServiceTest {

  @InjectMocks
  private PotService potService;

  @Mock
  private PotRepository repository;

  @Test
  void getAllPotsShouldReturnListOfPotsEntities() {
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();
    Pot wonderfulPot = Pot.builder().id(123L).name("wonderfulPot").availableQuantity(7).build();

    ArrayList<Pot> pots = new ArrayList<Pot>() {{
      add(superPuperPot);
      add(wonderfulPot);
    }};

    when(repository.findAll()).thenReturn(pots);

    List<Pot> allPots = potService.getAllPots();

    assertEquals(pots.size(), allPots.size());
    assertIterableEquals(pots, allPots);
  }
}
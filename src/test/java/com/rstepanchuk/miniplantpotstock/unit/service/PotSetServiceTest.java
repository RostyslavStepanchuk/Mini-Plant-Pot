package com.rstepanchuk.miniplantpotstock.unit.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.catalog.PotSet;
import com.rstepanchuk.miniplantpotstock.repository.PotSetRepository;
import com.rstepanchuk.miniplantpotstock.service.PotSetService;
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
class PotSetServiceTest {

  @InjectMocks
  private PotSetService potSetService;

  @Mock
  private PotSetRepository repository;

  @Test
  void getAllPotSetsShouldReturnListOfPotSets() {
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();
    Pot wonderfulPot = Pot.builder().id(123L).name("wonderfulPot").availableQuantity(7).build();
    Pot adorablePot = Pot.builder().id(321L).name("adorablePot").availableQuantity(9).build();

    ArrayList<Pot> pots = new ArrayList<Pot>() {{
      add(superPuperPot);
      add(wonderfulPot);
      add(adorablePot);
    }};



    PotSet setOfPerfectPots = PotSet.builder().id(938L).name("SetOfPerfectPots").pots(pots).build();
    ArrayList<PotSet> potSets = new ArrayList<PotSet>() {{
      add(setOfPerfectPots);
    }};

    when(repository.findAll()).thenReturn(potSets);
    List<PotSet> result = potSetService.getAllPotSets();
    assertIterableEquals(potSets, result);
  }
}
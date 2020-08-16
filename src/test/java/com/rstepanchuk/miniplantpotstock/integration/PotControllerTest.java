package com.rstepanchuk.miniplantpotstock.integration;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.catalog.PotSet;
import com.rstepanchuk.miniplantpotstock.repository.PotRepository;
import com.rstepanchuk.miniplantpotstock.repository.PotSetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PotControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PotRepository potRepository;
  @MockBean
  private PotSetRepository potSetRepository;

  @Test
  void getAllPotsShouldReturnResponseStatusOk() throws Exception {
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").variationAttributes(new HashSet<>()).availableQuantity(10).build();
    Pot wonderfulPot = Pot.builder().id(123L).name("wonderfulPot").variationAttributes(new HashSet<>()).availableQuantity(7).build();

    ArrayList<Pot> pots = new ArrayList<Pot>() {{
      add(superPuperPot);
      add(wonderfulPot);
    }};

    when(potRepository.findAll()).thenReturn(pots);

    mockMvc.perform(get("/api/v1/pots"))
        .andExpect(status().isOk());
  }

  @Test
  void getAllSkuShouldReturnResponseStatusOk() throws Exception {

    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").variationAttributes(new HashSet<>()).availableQuantity(10).build();
    Pot wonderfulPot = Pot.builder().id(123L).name("wonderfulPot").variationAttributes(new HashSet<>()).availableQuantity(7).build();
    Pot adorablePot = Pot.builder().id(321L).name("adorablePot").variationAttributes(new HashSet<>()).availableQuantity(9).build();

    ArrayList<Pot> pots = new ArrayList<Pot>() {{
      add(superPuperPot);
      add(wonderfulPot);
      add(adorablePot);
    }};



    PotSet setOfPerfectPots = PotSet.builder().id(938L).name("SetOfPerfectPots").pots(pots).build();
    ArrayList<PotSet> potSets = new ArrayList<PotSet>() {{
      add(setOfPerfectPots);
    }};

    when(potRepository.findAll()).thenReturn(new ArrayList<>());
    when(potSetRepository.findAll()).thenReturn(potSets);

    mockMvc.perform(get("/api/v1/pots/all"))
        .andExpect(status().isOk());
  }
}
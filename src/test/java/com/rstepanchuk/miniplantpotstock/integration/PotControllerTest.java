package com.rstepanchuk.miniplantpotstock.integration;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.catalog.PotSet;
import com.rstepanchuk.miniplantpotstock.entity.catalog.PotVariationAttribute;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Variation;
import com.rstepanchuk.miniplantpotstock.entity.catalog.VariationValue;
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

    Variation size = Variation.builder().id(1L).name("size").build();

    VariationValue small = VariationValue.builder().id(1L).value("S").variation(size).build();
    VariationValue big = VariationValue.builder().id(1L).value("L").variation(size).build();

    size.setValues(new HashSet<VariationValue>(){{add(small); add(big);}});

    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();
    Pot wonderfulPot = Pot.builder().id(123L).name("wonderfulPot").availableQuantity(7).build();

    PotVariationAttribute size1 = PotVariationAttribute.builder().pot(superPuperPot).variation(size).variationValue(small).build();
    PotVariationAttribute size2 = PotVariationAttribute.builder().pot(wonderfulPot).variation(size).variationValue(big).build();

    superPuperPot.setVariationAttributes(new HashSet<PotVariationAttribute>(){{add(size1);}});
    wonderfulPot.setVariationAttributes(new HashSet<PotVariationAttribute>(){{add(size2);}});

    ArrayList<Pot> pots = new ArrayList<Pot>() {{
      add(superPuperPot);
      add(wonderfulPot);
    }};

    when(potRepository.findAll()).thenReturn(pots);

    mockMvc.perform(get("/api/v1/catalog/pots"))
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

    mockMvc.perform(get("/api/v1/catalog/pots/all"))
        .andExpect(status().isOk());
  }
}
package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.PotSet;
import com.rstepanchuk.miniplantpotstock.repository.PotSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PotSetService {

  private final PotSetRepository repository;

  public List<PotSet> getAllPotSets() {
    return repository.findAll();
  }
}

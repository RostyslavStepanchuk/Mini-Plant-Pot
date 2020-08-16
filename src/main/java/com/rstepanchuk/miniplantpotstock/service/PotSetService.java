package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.PotSet;
import com.rstepanchuk.miniplantpotstock.repository.PotSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotSetService {

  private PotSetRepository repository;

  @Autowired
  public PotSetService(PotSetRepository repository) {
    this.repository = repository;
  }

  public List<PotSet> getAllPotSets() {
    return repository.findAll();
  }
}

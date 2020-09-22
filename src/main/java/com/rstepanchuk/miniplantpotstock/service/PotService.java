package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.repository.PotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class PotService {

  private PotRepository repository;

  @Autowired
  public PotService(PotRepository repository) {
    this.repository = repository;
  }

  public List<Pot> getAllPots() {
    return repository.findAll();
  }

}

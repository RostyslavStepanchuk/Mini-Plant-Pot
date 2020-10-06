package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Sku;
import com.rstepanchuk.miniplantpotstock.repository.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SkuService {

  private final SkuRepository repository;
  private final PotService potService;

  public List<Sku> getSkus() {
    return repository.findAll();
  }

  public List<Pot> getPots() {
    return potService.getPots();
  }

}

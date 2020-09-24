package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.production.supply.ProductionSupplyDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.production.ProductionSupply;
import com.rstepanchuk.miniplantpotstock.service.ProductionSupplyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProductionSupplyMapper {

  private final ModelMapper modelMapper;
  private final ProductionSupplyService service;

  private ProductionSupplyDtoOut responseDtoOf(ProductionSupply entity) {
    ProductionSupplyDtoOut dto = modelMapper.map(entity, ProductionSupplyDtoOut.class);
    dto.setPotId(entity.getPot().getId());
    return dto;
  }

  public List<ProductionSupplyDtoOut> getProductionSupplies() {
    return service.getProductionSupplies().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }
}

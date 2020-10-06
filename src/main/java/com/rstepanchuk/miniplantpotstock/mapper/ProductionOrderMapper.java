package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.production.order.ProductionOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.production.ProductionOrder;
import com.rstepanchuk.miniplantpotstock.service.ProductionOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProductionOrderMapper {

  private final ModelMapper modelMapper;
  private final ProductionOrderService service;

  private ProductionOrderDtoOut responseDtoOf(ProductionOrder entity) {
    ProductionOrderDtoOut dto = modelMapper.map(entity, ProductionOrderDtoOut.class);
    dto.setPotId(entity.getPot().getId());
    return dto;
  }

  public List<ProductionOrderDtoOut> getProductionOrders() {
    return service.getProductionOrders().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }
}

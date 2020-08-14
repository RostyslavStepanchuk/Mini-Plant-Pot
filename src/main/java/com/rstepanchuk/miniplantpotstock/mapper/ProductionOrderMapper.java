package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.production.order.ProductionOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.production.ProductionOrder;
import com.rstepanchuk.miniplantpotstock.service.ProductionOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductionOrderMapper {

  private ModelMapper modelMapper;
  private ProductionOrderService service;

  @Autowired
  public ProductionOrderMapper(ModelMapper modelMapper, ProductionOrderService service) {
    this.modelMapper = modelMapper;
    this.service = service;
  }

  private ProductionOrderDtoOut entityOf(ProductionOrder entity) {
    ProductionOrderDtoOut dto = modelMapper.map(entity, ProductionOrderDtoOut.class);
    dto.setOrderedPotId(entity.getOrderedPot().getId());
    return dto;
  }

  public List<ProductionOrderDtoOut> getProductionOrders() {
    return service.getProductionOrders().stream()
        .map(this::entityOf)
        .collect(Collectors.toList());
  }
}

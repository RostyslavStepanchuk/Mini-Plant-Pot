package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.order.CustomerOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomerOrderMapper {

  private final ModelMapper modelMapper;
  private final SkuMapper skuMapper;
  private final CustomerOrderService service;

  private CustomerOrderDtoOut responseDtoOf(CustomerOrder entity) {
    CustomerOrderDtoOut dto = modelMapper.map(entity, CustomerOrderDtoOut.class);
    dto.setOrderedSku(skuMapper.responseDtoOf(entity.getSku()));
    return dto;
  }

  public List<CustomerOrderDtoOut> getCustomerOrders() {
    return service.getCustomerOrders().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }
}

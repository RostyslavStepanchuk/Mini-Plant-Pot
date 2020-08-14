package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.order.CustomerOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerOrderMapper {

  private ModelMapper modelMapper;
  private CustomerOrderService service;

  @Autowired
  public CustomerOrderMapper(ModelMapper modelMapper, CustomerOrderService service) {
    this.modelMapper = modelMapper;
    this.service = service;
  }

  private CustomerOrderDtoOut responseDtoOf(CustomerOrder entity) {
    CustomerOrderDtoOut dto = modelMapper.map(entity, CustomerOrderDtoOut.class);
    dto.setPotsIds(entity.getPots().stream().map(Pot::getId).collect(Collectors.toList()));
    return dto;
  }

  public List<CustomerOrderDtoOut> getCustomerOrders() {
    return service.getCustomerOrders().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }
}

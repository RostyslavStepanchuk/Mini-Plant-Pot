package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.order.CustomerOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderMapper {

  private ModelMapper modelMapper;
  private CustomerOrderService service;

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
    CustomerOrder testOrder = CustomerOrder.builder().id(333L).pots(new ArrayList<>()).isClosed(false).build();
    CustomerOrder customerOrder = service.saveCustomerOrder(testOrder);
    return service.getCustomerOrders().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }
}

package com.rstepanchuk.miniplantpotstock.dto.production.supply;

import lombok.Data;

import java.util.Date;

@Data
public class ProductionSupplyDtoOut {
  private Long id;
  private Date supplyDate;
  private Long potId;
  private Integer suppliedQuantity;
  private Boolean wasOrdered;
}

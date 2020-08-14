package com.rstepanchuk.miniplantpotstock.dto.production.order;

import lombok.Data;

import java.util.Date;

@Data
public class ProductionOrderDtoOut {
  private Integer id;
  private Long orderedPotId;
  private Date submitDate;
  private Date initialDeadline;
  private Integer deadlineChanges;
  private Date currentDeadline;
  private Integer orderedQuantity;
  private Integer fulfilledQuantity;
  private Date delivered;
}

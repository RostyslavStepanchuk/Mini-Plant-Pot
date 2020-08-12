package com.rstepanchuk.miniplantpotstock.dto.pot;

import lombok.Data;

@Data
public abstract class SkuDtoOut {
  private Long id;
  private String name;
  private String type;
  private Integer availableQuantity;
}

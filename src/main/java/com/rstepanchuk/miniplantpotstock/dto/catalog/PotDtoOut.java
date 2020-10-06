package com.rstepanchuk.miniplantpotstock.dto.catalog;

import lombok.Data;

import java.util.List;

@Data
public class PotDtoOut {
  private Long id;
  private String name;
  private Integer availableQuantity;
  private List<PotVariationAttrDtoOut> variationModel;
}

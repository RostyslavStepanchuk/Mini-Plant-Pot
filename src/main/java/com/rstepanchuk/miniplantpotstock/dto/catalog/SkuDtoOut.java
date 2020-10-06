package com.rstepanchuk.miniplantpotstock.dto.catalog;

import lombok.Data;

import java.util.List;

@Data
public class SkuDtoOut {
  private String id;
  private boolean isSet;
  private int quantity;
  private List<PotDtoOut> content;
}

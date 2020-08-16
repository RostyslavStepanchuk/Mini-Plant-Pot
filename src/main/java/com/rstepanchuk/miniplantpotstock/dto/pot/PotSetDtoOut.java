package com.rstepanchuk.miniplantpotstock.dto.pot;

import lombok.Data;

import java.util.List;

@Data
public class PotSetDtoOut extends SkuDtoOut {
  List<PotDtoOut> productSetPots;
}

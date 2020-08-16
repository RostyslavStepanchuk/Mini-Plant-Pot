package com.rstepanchuk.miniplantpotstock.dto.pot;

import lombok.Data;

import java.util.List;

@Data
public class PotDtoOut extends SkuDtoOut {

  private List<PotVariationAttrDtoOut> variationModel;
}

package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.catalog.PotDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.catalog.PotVariationAttrDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.catalog.SkuDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Sku;
import com.rstepanchuk.miniplantpotstock.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SkuMapper {

  private final ModelMapper modelMapper;
  private final SkuService skuService;

  PotDtoOut responseDtoOf(Pot entity) {
    PotDtoOut dto = modelMapper.map(entity, PotDtoOut.class);
    List<PotVariationAttrDtoOut> variationModel =
        Optional.ofNullable(entity.getVariationAttributes())
            .orElse(new HashSet<>())
            .stream()
        .map(attribute-> {
          PotVariationAttrDtoOut attrDtoOut = new PotVariationAttrDtoOut();
          attrDtoOut.setAttribute(attribute.getVariation().getName());
          attrDtoOut.setValue(attribute.getVariationValue().getValue());
          return attrDtoOut;
        })
        .collect(Collectors.toList());
    dto.setVariationModel(variationModel);
    return dto;
  }

  SkuDtoOut responseDtoOf(Sku entity) {
    SkuDtoOut sku = modelMapper.map(entity, SkuDtoOut.class);
    List<PotDtoOut> pots = entity.getPots()
        .stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
    sku.setContent(pots);
    return sku;
  }

  public List<PotDtoOut> getPots() {
    return skuService.getPots().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }

  public List<SkuDtoOut> getSkus() {
    return skuService.getSkus().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }
}

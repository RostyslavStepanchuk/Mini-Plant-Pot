package com.rstepanchuk.miniplantpotstock.mapper;

import com.rstepanchuk.miniplantpotstock.dto.pot.PotDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.pot.PotSetDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.pot.PotVariationAttrDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.pot.SkuDtoOut;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.catalog.PotSet;
import com.rstepanchuk.miniplantpotstock.service.PotService;
import com.rstepanchuk.miniplantpotstock.service.PotSetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PotMapper {

  private ModelMapper modelMapper;
  private PotService potService;
  private PotSetService potSetService;

  public PotMapper(ModelMapper modelMapper, PotService service, PotSetService potSetService) {
    this.modelMapper = modelMapper;
    this.potService = service;
    this.potSetService = potSetService;
  }

  PotDtoOut responseDtoOf(Pot entity) {
    PotDtoOut dto = modelMapper.map(entity, PotDtoOut.class);
    List<PotVariationAttrDtoOut> variationModel = entity.getVariationAttributes().stream()
        .map(attribute-> {
          PotVariationAttrDtoOut attrDtoOut = new PotVariationAttrDtoOut();
          attrDtoOut.setAttribute(attribute.getVariation().getName());
          attrDtoOut.setValue(attribute.getVariationValue().getValue());
          return attrDtoOut;
        })
        .collect(Collectors.toList());
    dto.setVariationModel(variationModel);
    dto.setType(Pot.class.getSimpleName());
    return dto;
  }

  PotSetDtoOut responseDtoOf(PotSet entity) {
    PotSetDtoOut setDto = modelMapper.map(entity, PotSetDtoOut.class);
    setDto.setType(PotSet.class.getSimpleName());
    List<PotDtoOut> productSetPots = entity.getPots().stream().map(this::responseDtoOf).collect(Collectors.toList());
    setDto.setProductSetPots(productSetPots);
    return setDto;
  }

  public List<PotDtoOut> getAllPots() {
    return potService.getAllPots().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }

  private List<SkuDtoOut> getAllPotSets() {
    return potSetService.getAllPotSets().stream()
        .map(this::responseDtoOf)
        .collect(Collectors.toList());
  }

  public List<SkuDtoOut> getAllSku() {
    ArrayList<SkuDtoOut> result = new ArrayList<>();
    result.addAll(getAllPots());
    result.addAll(getAllPotSets());
    return result;
  }

}

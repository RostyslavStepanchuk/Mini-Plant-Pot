package com.rstepanchuk.miniplantpotstock.config;

import com.rstepanchuk.miniplantpotstock.mapper.CustomerOrderMapper;
import com.rstepanchuk.miniplantpotstock.mapper.PotMapper;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionOrderMapper;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionSupplyMapper;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import com.rstepanchuk.miniplantpotstock.repository.PotRepository;
import com.rstepanchuk.miniplantpotstock.repository.PotSetRepository;
import com.rstepanchuk.miniplantpotstock.repository.ProductionOrderRepository;
import com.rstepanchuk.miniplantpotstock.repository.ProductionSupplyRepository;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import com.rstepanchuk.miniplantpotstock.service.EtsyService;
import com.rstepanchuk.miniplantpotstock.service.PotService;
import com.rstepanchuk.miniplantpotstock.service.PotSetService;
import com.rstepanchuk.miniplantpotstock.service.ProductionOrderService;
import com.rstepanchuk.miniplantpotstock.service.ProductionSupplyService;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyAuthMgr;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyClient;
import com.rstepanchuk.miniplantpotstock.util.FormDataParser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  //     <----- Mapper ----->
  @Bean
  public CustomerOrderMapper customerOrderMapper(ModelMapper modelMapper, CustomerOrderService customerOrderService) {
    return new CustomerOrderMapper(modelMapper, customerOrderService);
  }

  @Bean
  public PotMapper potMapper(ModelMapper modelMapper, PotService potService, PotSetService potSetService) {
    return new PotMapper(modelMapper, potService, potSetService);
  }

  @Bean
  public ProductionOrderMapper productionOrderMapper(ModelMapper modelMapper,
                                                     ProductionOrderService productionOrderService) {
    return new ProductionOrderMapper(modelMapper, productionOrderService);
  }

  @Bean
  public ProductionSupplyMapper productionSupplyMapper(ModelMapper modelMapper,
                                                       ProductionSupplyService productionSupplyService) {
    return new ProductionSupplyMapper(modelMapper, productionSupplyService);
  }

  //      <----- Service ----->
  @Bean
  public CustomerOrderService customerOrderService(CustomerOrderRepository customerOrderRepository) {
    return new CustomerOrderService(customerOrderRepository);
  }

  @Bean
  public PotService potService(PotRepository potRepository) {
    return new PotService(potRepository);
  }

  @Bean
  public PotSetService potSetService(PotSetRepository potSetRepository) {
    return new PotSetService(potSetRepository);
  }

  @Bean
  public ProductionOrderService productionOrderService(ProductionOrderRepository productionOrderRepository) {
    return new ProductionOrderService(productionOrderRepository);
  }

  @Bean
  public ProductionSupplyService productionSupplyService(ProductionSupplyRepository productionSupplyRepository) {
    return new ProductionSupplyService(productionSupplyRepository);
  }

  @Bean
  public EtsyService etsyService(EtsyClient etsyClient) {
    return new EtsyService(etsyClient);
  }

  //      <----- Integration Etsy ----->

  @Bean
  public EtsyClient etsyClient(EtsyAuthMgr etsyAuthMgr, FormDataParser formDataParser) {
    return new EtsyClient(etsyAuthMgr, formDataParser);
  }

  @Bean
  public EtsyAuthMgr etsyAuthMgr() {
    return new EtsyAuthMgr();
  }

  //      <----- Utils ----->

  @Bean
  public FormDataParser formDataParser() {
    return new FormDataParser();
  }


}
